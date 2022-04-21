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
  constructor() { }

  ngOnInit(): void {
  }

  OnVerCrearPasajero(){
    this.verLogin=false;
    this.verCrearVuelo=false;
    this.verCrearPasajero=true;
    this.verReserva=false;
    this.verCrearTripu=false;
  }
  OnVerCrearVuelo(){
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=true;
    this.verReserva=false;
    this.verCrearTripu=false;
  }
  OnVerReserva(){
    this.verReserva=true;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
  }
  OnVerCrearTripu(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=true;
  }

}
