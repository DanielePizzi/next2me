import { Component, OnInit, ViewChild, ElementRef, NgZone, ChangeDetectionStrategy, Input } from '@angular/core';
import { AgmMap } from '@agm/core';
import { MapsAPILoader } from '@agm/core';
import { FormControl } from '@angular/forms';
import { placeEnum } from '../shared/enum/placeEnum';
import {NgbModal, NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import { PointOfInterest } from '../model/pointOfInterest';
import Utils from '../shared/utils/Utils';
import { LoaderService } from '../core/services/loader.service';
declare var google: any;

@Component({
  selector: 'ngbd-modal-content',
  styles: ['.modal-header{background-color: yellow;}'] ,
  template: `
    <div class="modal-header">
      <h4 class="modal-title">Attenzione</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <p>{{name}}!</p>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Close click')">Close</button>
    </div>
  `
})

export class NgbdModalContent {
  @Input() name;

  constructor(public activeModal: NgbActiveModal) {}
}


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class HomePageComponent implements OnInit {

  latMap: number;
  lngMap: number;
  latPosition: number;
  lngPosition: number;
  zoom: number;
  toogle: boolean = false;
  listOfPlace: any = placeEnum.listOFplace;
  placeSelected:any;
  placeWritten:any;
  markers: marker[] = [];
  pointOfInteresList: PointOfInterest[] = [];
  private map: any;
  public searchControl: FormControl;

  @ViewChild("search")
  public searchElementRef: ElementRef;

  @ViewChild(AgmMap)
  public agmMap: AgmMap

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private modalService: NgbModal,
    private loaderService : LoaderService,
  ) {}

  ngOnInit() {
    this.zoom = 12;
    this.caricaAutocompletamentoMappa();
  }

  mapReady($event: any) {
    this.caricaMappa($event);
  }

  private caricaAutocompletamentoMappa(){
    this.searchControl = new FormControl();
    //load Places Autocomplete

    this.mapsAPILoader.load().then(() => {
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //set latitude, longitude and zoom
          this.latMap = place.geometry.location.lat();
          this.lngMap = place.geometry.location.lng();
          this.latPosition = place.geometry.location.lat();
          this.lngPosition = place.geometry.location.lng();
        });
      });
    });
  }

  private caricaMappa(map: any) {
    this.map = map;

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((posizione) => {
      this.mostraPosizione(posizione);
      });
    } else {
      alert("Geolocalizzazione non supportata sul tuo brownser");
    }
  }

  cercaDaQuery(){
    let latLng = new google.maps.LatLng(this.latPosition, this.lngPosition);
    let placeService = new google.maps.places.PlacesService(this.map);
    this.loaderService.show();
    placeService.findPlaceFromQuery({
      query: this.placeWritten,
      fields: ['photos', 'formatted_address', 'name', 'rating', 'opening_hours', 'geometry'],
    }, (results, status) => {
      this.callback(results, status)
      this.loaderService.hide();
    });
  }

  cercaDaCategoria(){
    let keySelected;
    Object.keys(this.listOfPlace).forEach(key => {
      if (this.listOfPlace[key].value === this.placeSelected) {
          keySelected = this.listOfPlace[key].key;
      }
    });
    let latLng = new google.maps.LatLng(this.latPosition, this.lngPosition);
    let placeService = new google.maps.places.PlacesService(this.map);
    this.loaderService.show();
    placeService.nearbySearch({
      location: latLng,
      radius: 2000,
      type: [keySelected]
    }, (results, status) => {
      this.loaderService.hide();
      this.callback(results, status)
    });
  }

  callback(results, status) {
    this.markers = [];
    this.pointOfInteresList = []
    if (status === google.maps.places.PlacesServiceStatus.OK) {
      const markerT = results.map( (element) => {
        return  {
          lat: +element.geometry.location.lat(),
          lng: +element.geometry.location.lng(),
          draggable: false,
          name: element.name }
        })
      this.pointOfInteresList = results.map((element) => {
        return  {
          id: element.id,
          via: element.vicinity,
          nome: element.name,
          distanza: Utils.calculateDistanceFromPoint(this.latPosition,this.lngPosition,+element.geometry.location.lat(),+element.geometry.location.lng(),'K'),
          stato: element.opening_hours ? element.opening_hours.open_now : false,
          rating: element.rating,
          lat: +element.geometry.location.lat(),
          lng: +element.geometry.location.lng()
        }
      })
      this.pointOfInteresList.sort(function(a,b){
        return Number(a.distanza) - Number(b.distanza);
      })
      this.markers = markerT;
      this.agmMap.triggerResize();
    } else {
      this.agmMap.triggerResize();
      const modalRef = this.modalService.open(NgbdModalContent,{ centered: true });
      modalRef.componentInstance.name = 'NESSUN PUNTO DI INTERESSE TROVATO';
    }
  }

  private mostraPosizione(position) {
    this.latMap = position.coords.latitude;
    this.lngMap = position.coords.longitude;
    this.latPosition = position.coords.latitude;
    this.lngPosition = position.coords.longitude;
  }

  goToPosition(point) {
    this.latMap = point.lat;
    this.lngMap = point.lng;
    this.zoom = 18;
    window.scrollTo({ left: 0, top: 0, behavior: 'smooth' });
  }

}
