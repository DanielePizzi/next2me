import { Component, OnInit, ViewChild, ElementRef, NgZone, ChangeDetectionStrategy } from '@angular/core';
import { MapsAPILoader } from '@agm/core';
import { FormControl } from '@angular/forms';
import { placeEnum } from '../shared/enum/placeEnum';
declare var google: any;

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class HomePageComponent implements OnInit {

  lat: number;
  lng: number;
  zoom: number;
  toogle: boolean = false;
  listOfPlace: any = placeEnum.listOFplace;
  placeSelected:any;
  placeWritten:any;
  markers: marker[] = [];
  private map: any;
  public searchControl: FormControl;

  @ViewChild("search")
  public searchElementRef: ElementRef;

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) {}

  ngOnInit() {
    this.zoom = 12;
    this.caricaAutocompletamentoMappa();
  }

  toogleChange(){
    this.toogle = !this.toogle;
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
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();
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
    let latLng = new google.maps.LatLng(this.lat, this.lng);
    let placeService = new google.maps.places.PlacesService(this.map);
    placeService.findPlaceFromQuery({
      query: this.placeWritten,
      fields: ['photos', 'formatted_address', 'name', 'rating', 'opening_hours', 'geometry'],
    }, (results, status) => {
      this.callback(results, status)
    });
  }

  cercaDaCategoria(){
    let keySelected;
    Object.keys(this.listOfPlace).forEach(key => {
      if (this.listOfPlace[key].value === this.placeSelected) {
          keySelected = this.listOfPlace[key].key;
      }
    });
    let latLng = new google.maps.LatLng(this.lat, this.lng);
    let placeService = new google.maps.places.PlacesService(this.map);
    placeService.nearbySearch({
      location: latLng,
      radius: 2000,
      type: [keySelected]
    }, (results, status) => {
      this.callback(results, status)
    });
  }

  callback(results, status) {
    this.markers = [];
    if (status === google.maps.places.PlacesServiceStatus.OK) {
      results.forEach(element => {
        this.markers.push({
          lat: +element.geometry.location.lat(),
          lng: +element.geometry.location.lng(),
          draggable: false,
          name: element.name
        })
      });
    } else {
      alert("Servizio non disponibile")
    }
  }

  private mostraPosizione(position) {
    this.lat = position.coords.latitude;
    this.lng = position.coords.longitude;
  }

}
