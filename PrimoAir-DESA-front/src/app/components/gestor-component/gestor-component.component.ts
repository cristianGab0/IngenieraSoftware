import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'gestor-component',
  templateUrl: './gestor-component.component.html',
  styleUrls: ['./gestor-component.component.css']
})
export class GestorComponentComponent implements OnInit {
  verLogin:boolean=false;
  verCrearPasajero=false;
  verCrearVuelo=false;
  verReserva=false;
  constructor() { }

  ngOnInit(): void {
  }

  OnVerCrearPasajero(){
    this.verLogin=false;
    this.verCrearVuelo=false;
    this.verCrearPasajero=true;
    this.verReserva=false;
  }
  OnVerCrearVuelo(){
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=true;
    this.verReserva=false;
  }
  OnVerReserva(){
    this.verReserva=true;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
  }

}
