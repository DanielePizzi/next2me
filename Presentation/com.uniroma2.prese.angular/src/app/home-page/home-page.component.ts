import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
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

  constructor() {
  }

  ngOnInit() {
    this.zoom = 10;
  }

  mapReady($event: any) {
    this.getNearbyPlace($event);
  }

  getNearbyPlace(map: any) {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
      this.showPosition(position);
      let latLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
      let placeService = new google.maps.places.PlacesService(map);
        placeService.nearbySearch({
          location: latLng,
          radius: 500,
          type: ['pub']
        }, (results, status) => {
            this.callback(results, status)
        });
      });
    } else {
      alert("Geolocation is not supported by this browser.");
    }
  }

  callback(results, status) {
    if (status === google.maps.places.PlacesServiceStatus.OK) {
      for (var i = 0; i < results.length; i++) {
        console.log(results)
      }
    }
  }

  private showPosition(position) {
    this.lat = position.coords.latitude;
    this.lng = position.coords.longitude;
  }

}
