import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'gestor-component',
  templateUrl: './gestor-component.component.html',
  styleUrls: ['./gestor-component.component.css']
})
export class GestorComponentComponent implements OnInit {
  verLogin:boolean=true;
  verCrearPasajero=false;
  constructor() { }

  ngOnInit(): void {
  }

  OnVerCrearPasajero(){
    this.verLogin=false;
    this.verCrearPasajero=true;
  }

}
