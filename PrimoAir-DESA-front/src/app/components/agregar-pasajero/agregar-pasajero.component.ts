import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'agregar-pasajero',
  templateUrl: './agregar-pasajero.component.html',
  styleUrls: ['./agregar-pasajero.component.css']
})
export class AgregarPasajeroComponent implements OnInit {

  array=[true,true,true,true,true,true]
  array2:any=[]
  constructor() { }

  ngOnInit(): void {
    for(let i=0; i<26;i++){
      this.array2.push([true,true,true,true,true,true])
    }
    this.array2[2][1]=false;
    console.log(JSON.stringify(this.array2[2][1]))


  }

}
