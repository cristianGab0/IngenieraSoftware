import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'gestor-component',
  templateUrl: './gestor-component.component.html',
  styleUrls: ['./gestor-component.component.css']
})
export class GestorComponentComponent implements OnInit {
  verLogin:boolean=true;
  verCrearPasajero=false;
  verCrearVuelo=false;
  verCrearTripu=false;
  verReserva=false;
  verReporteVuelo=false;
  verConsultarVuelos=false;
  verConsultarAerolineas=false;
  constructor() { }

  ngOnInit(): void {
  }

  OnVerCrearPasajero(){
    this.verLogin=false;
    this.verCrearVuelo=false;
    this.verCrearPasajero=true;
    this.verReserva=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
  }
  OnVerCrearVuelo(){
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=true;
    this.verReserva=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
  }
  OnVerReserva(){
    this.verReserva=true;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
  }
  OnVerCrearTripu(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=true;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
  }
  OnVerReporteVuelo(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=true;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
  }
  OnVerConsultarVuelos(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=true;
    this.verConsultarAerolineas=false;
  }
  OnVerConsultarAerolineas(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=true;
  }
}
