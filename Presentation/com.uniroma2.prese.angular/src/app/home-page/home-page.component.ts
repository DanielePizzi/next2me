import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { MapsAPILoader } from '@agm/core';
import { FormControl } from '@angular/forms';
declare var google: any;

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  lat: number;
  lng: number;
  zoom: number;
  map: any;
  public searchControl: FormControl;

  @ViewChild("search")
  public searchElementRef: ElementRef;

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone
  ) {}

  ngOnInit() {
    this.zoom = 10;
    this.loadAutocomplete();

  }

  mapReady($event: any) {
    this.loadMap($event);
  }

  private loadAutocomplete(){
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

  private loadMap(map: any) {
    this.map = map;

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
      this.showPosition(position);
      // let latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
      // let placeService = new google.maps.places.PlacesService(map);
      //   placeService.nearbySearch({
      //     location: latLng,
      //     radius: 500,
      //     type: ['pub']
      //   }, (results, status) => {
      //       this.callback(results, status)
      //   });
      });
    } else {
      alert("Geolocalizzazione non supportata sul tuo brownser");
    }
  }

  // callback(results, status) {
  //   if (status === google.maps.places.PlacesServiceStatus.OK) {
  //     for (var i = 0; i < results.length; i++) {
  //       console.log(results)
  //     }
  //   }
  // }

  private showPosition(position) {
    this.lat = position.coords.latitude;
    this.lng = position.coords.longitude;
  }

}
