import { Injectable } from "@angular/core";
import { Http, Headers, Request, RequestOptions, RequestMethod, Response} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { ENDPOINTS_MAP } from './endpoints-map';
import { EnvironmentService } from "../environment-service/environment-service";
import { Subscription } from "rxjs/Subscription";
import { LoaderService } from "../../core/services/loader.service";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { NgbdModalContentError } from "../../app.component";
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class ApiService {

  private environment: any;
  private envsubscription: Subscription;
  private baseUrl: string;

  constructor(
    private http: Http,
    public environmentService: EnvironmentService,
    private loaderService : LoaderService,
    private modalService: NgbModal
  ) {
    this.envsubscription = this.environmentService.get().subscribe((data) => {
      if (data) {
        this.environment = data;
        this.baseUrl = this.environment.apiUrl;
      }
    });
  }

  post(url: string, body: Object,) {
    return this.request(url, RequestMethod.Post, body);
  }

  request(url: string, method: RequestMethod, body?: Object, queryParams?: any, pathParams?: any[]) {
    const headers: Headers = this.headersManager(method);

    const requestOptions = new RequestOptions({
      url: this.getEnpointCorrente(url),
      method: method,
      headers: headers
    });

    if (body) {
      requestOptions.body = body;
    }

    const request = new Request(requestOptions);
    this.loaderService.show();
    return this.http.request(request)
      .map((res: Response) => {
        this.loaderService.hide();
        return res.json();
      })
      // .catch((res: Response) => this.requestError(res));
      .catch((err:Response) =>{
        return Observable.throw(this.errorHandler(err));
     });
  }

  private headersManager(requestMethod: RequestMethod): Headers {
    const headers = new Headers();
    return headers;
  }

  private getEnpointCorrente(url: string): string {

    let endpoint: string;

    //controllo che il servizio sia mappato
    if (!ENDPOINTS_MAP[url]) {
      console.warn(`Servizio '${url}' non trovato`)
      this.loaderService.hide();
      endpoint = url;
    }
    //controllo che il servizio sia mappato correttaente nell'enpoint corrente.
    else if ((!ENDPOINTS_MAP[url][this.environmentService.selectedEnvironment.name])) {
      console.warn(`Servizio '${url}' non mappato '${this.environmentService.selectedEnvironment.name}' nell'endpoint dell'environments`)
      this.loaderService.hide();
      endpoint = url;
    }

    else endpoint = `${this.baseUrl}${ENDPOINTS_MAP[url][this.environmentService.selectedEnvironment.name]}`;

    return endpoint;

  }

  errorHandler(error: any): void {
    this.loaderService.hide();
    let details = error.json();
    const modalRef = this.modalService.open(NgbdModalContentError,{ centered: true });
    modalRef.componentInstance.title = error.status + ' ,' + error.statusText;
    if (details && details.errorList) {
      details.errorList = details.errorList.map(function(item) {
        return item = item.replace(/_/g, ' ');
      });
      modalRef.componentInstance.errors = details.errorList;
    } else {
      modalRef.componentInstance.errors = ['Codice di errore non trovato']
    }
  }

  // private requestError(res: Response | any) {
  //   let errMsg: string;
  //   if (res instanceof Response) {
  //     const err = res || '';
  //     errMsg = `${res.status} - ${res.statusText || ''} ${err}`;
  //   } else {
  //     errMsg = res.message ? res.message : res.toString();
  //   }
  //   let dataError = new ResponseError();
  //   dataError.code = res.status;
  //   dataError.message = res.statusText;
  //   dataError.url = res.url;
  //   dataError.body = (res && res.json()) || {};
  //   return Observable.throw(dataError);
  // }

}
