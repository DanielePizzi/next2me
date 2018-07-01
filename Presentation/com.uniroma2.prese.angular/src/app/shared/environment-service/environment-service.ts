import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import {BehaviorSubject} from "rxjs/BehaviorSubject"


@Injectable()
export class EnvironmentService {

  public selectedEnvironment: any = {};
  public environments: any[] = [];
  private environment = new BehaviorSubject<any>({});

  constructor(
      private loadedenvironments: any,
      private defaultenvironment:any
  ) {
      this.selectedEnvironment = defaultenvironment;
      this.environments        = loadedenvironments;
      this.environment.next(this.selectedEnvironment);
  }

  getAll(){
      return this.environments;   // passato da 'load-env.js' script prima di bootstrap
  }

  setNew(environment: any) {
      this.selectedEnvironment = environment;
      this.environment.next(environment);
  }

  get(): Observable<any>{
      return this.environment.asObservable();
  }

}
