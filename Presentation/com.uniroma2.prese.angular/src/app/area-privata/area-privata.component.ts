import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { placeEnum } from '../shared/enum/placeEnum';
import { PointOfInterest } from '../model/pointOfInterest';
import { FormControl } from '@angular/forms';
import { AgmMap, MapsAPILoader } from '@agm/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoaderService } from '../core/services/loader.service';

@Component({
  selector: 'app-area-privata',
  templateUrl: './area-privata.component.html',
  styleUrls: ['./area-privata.component.scss']
})
export class AreaPrivataComponent implements OnInit {

  latMap: number;
  lngMap: number;
  latPosition: number;
  lngPosition: number;
  latPointToSave: number;
  lngPointToSave: number;
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
  ) { }

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

  mapClicked($event: any) {
    this.latPointToSave = $event.coords.lat;
    this.lngPointToSave = $event.coords.lng;
    this._toggleSidebar();
  }

  markerDragEnd($event: any) {
    this.latPointToSave = $event.coords.lat;
    this.lngPointToSave = $event.coords.lng;
    this._toggleSidebar();
  }

  clickedMarker() {
    this._toggleSidebar();
  }

  // ********************************************
  // * SIDEBAR ELEMENT FUCTION ******************
  // ********************************************

  private _opened: boolean = false;
  private _showBackdrop: boolean = true;
  private _closeOnClickOutside: boolean = true;
  private _closeOnClickBackdrop: boolean = true;
  private _mode: string = 'push';

  private _toggleSidebar() {
    this._opened = !this._opened;
  }

  private cancellaPuntoInteresseDaSalvare() {
    this.latPointToSave = null
    this.lngPointToSave = null
    this._toggleSidebar();
  }

}
