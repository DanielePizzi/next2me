import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { placeEnum } from '../shared/enum/placeEnum';
import { PointOfInterest } from '../model/pointOfInterest';
import { FormControl } from '@angular/forms';
import { AgmMap, MapsAPILoader } from '@agm/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoaderService } from '../core/services/loader.service';
import { AreaPrivataService } from './area-privata.service';

@Component({
  selector: 'app-area-privata',
  templateUrl: './area-privata.component.html',
  styleUrls: ['./area-privata.component.scss'],
})
export class AreaPrivataComponent implements OnInit {

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


  /*FORM SALVA PUNTO INTERESSE*/
  latPointToSave: number;
  lngPointToSave: number;
  nome: string;
  citta: string;
  stato: string;
  categoria: any;
  descrizione: string;

  @ViewChild("search")
  public searchElementRef: ElementRef;

  @ViewChild(AgmMap)
  public agmMap: AgmMap

  constructor(
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private modalService: NgbModal,
    private loaderService : LoaderService,
    private areaPrivataService: AreaPrivataService
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
    // this._toggleSidebar();
  }

  markerDragEnd($event: any) {
    this.latPointToSave = $event.coords.lat;
    this.lngPointToSave = $event.coords.lng;
    // this._toggleSidebar();
  }

  clickedMarker() {
    this._toggleSidebar();
  }

  cercaDaCategoria(){
    let keySelected;
    Object.keys(this.listOfPlace).forEach(key => {
      if (this.listOfPlace[key].value === this.placeSelected) {
          keySelected = this.listOfPlace[key].key;
      }
    });
    let puntoDiInteresse = {
      username: '',
      categoria: keySelected,
      latitudine: this.latPosition,
      longitudine: this.lngPosition
    }
    this.areaPrivataService.getPuntoInteresse(puntoDiInteresse).subscribe(data => {
      this.callback(data)
    })
  }

  callback(results) {
    this.markers = [];
    this.pointOfInteresList = []
    const markerT = results.pointOfInterest.map( (element) => {
      return  {
        lat: +element.geometry.location.lat,
        lng: +element.geometry.location.lng,
        draggable: false,
        name: element.nome }
      })
    this.pointOfInteresList = results.pointOfInterest.map((element) => {
      return  {
        id: element.id,
        descrizione: element.descrizione,
        nome: element.nome,
        distanza: +element.geometry.location.distanza,
        tipo: element.tipo,
        stato: element.opening_hours ? element.opening_hours.open_now : false,
        rating: element.rating,
        lat: +element.geometry.location.lat,
        lng: +element.geometry.location.lng
      }
    })
    this.pointOfInteresList.sort(function(a,b){
      return Number(a.distanza) - Number(b.distanza);
    })
    this.markers = markerT;
    this.agmMap.triggerResize();
  }

  eliminaPuntoInteresse(point:any) {
    let puntoDiInteresseDaEliminare = {
      idPoint: point.id,
    }
    this.areaPrivataService.rimuoviPuntoInteresse(puntoDiInteresseDaEliminare).subscribe(data => {
      let puntoDiInteresse = {
        username: '',
        categoria: point.tipo,
        latitudine: this.latPosition,
        longitudine: this.lngPosition
      }
      this.areaPrivataService.getPuntoInteresse(puntoDiInteresse).subscribe(data => {
        this.callback(data)
      }, err => {
        this.markers = [];
        this.pointOfInteresList = []
      })
    })
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

  private clearForm(){
    this.nome = "";
    this.citta = "";
    this.stato = "";
    this.categoria = "";
    this.descrizione = "";
  }

  private salvaPuntoInteresse() {
    let puntoDiInteresse = {
      username: '',
      pointOfInterest : {
        nome: this.nome,
        citta: this.citta,
        stato: this.stato,
        tipo: this.categoria.key,
        descrizione: this.descrizione,
        geometry: {
          location: {
            lat: this.latPointToSave.toString(),
            lng: this.lngPointToSave.toString(),
          }
        }
      },
    }
    this.areaPrivataService.salvaPuntoInteresse(puntoDiInteresse).subscribe(data => {
      this._toggleSidebar();
      this.latPointToSave = null
      this.lngPointToSave = null
      this.clearForm();
    })
  }

}
