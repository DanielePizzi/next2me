import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { MapsAPILoader } from '@agm/core';
import { FormControl } from '@angular/forms';
declare var google: any;

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  lat: number;
  lng: number;
  zoom: number;
  private map: any;
  public searchControl: FormControl;

  @ViewChild("search")
  public searchElementRef: ElementRef;

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) {}

  ngOnInit() {
    this.zoom = 10;
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
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();
          this.zoom = 12;
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

  private cercaDaQuery(){
    let latLng = new google.maps.LatLng(this.lat, this.lng);
    let placeService = new google.maps.places.PlacesService(this.map);
    placeService.findPlaceFromQuery({
      location: latLng,
      radius: 500,
      type: ['pub']
    }, (results, status) => {
      this.callback(results, status)
    });
  }

  private cercaDaCategoria(){
    let latLng = new google.maps.LatLng(this.lat, this.lng);
    let placeService = new google.maps.places.PlacesService(this.map);
    placeService.nearbySearch({
      location: latLng,
      radius: 500,
      type: ['pub']
    }, (results, status) => {
      this.callback(results, status)
    });
  }

  callback(results, status) {
    if (status === google.maps.places.PlacesServiceStatus.OK) {
      for (var i = 0; i < results.length; i++) {
        console.log(results)
      }
    } else {
      alert("Servizio non disponibile")
    }
  }

  private mostraPosizione(position) {
    this.lat = position.coords.latitude;
    this.lng = position.coords.longitude;
  }

}
