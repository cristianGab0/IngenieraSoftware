import { GestorService } from './../../services/gestor.service';
import { Component, OnInit } from '@angular/core';
import { Fechas } from 'src/app/interfaces/fechas';
import * as $ from 'jquery';
@Component({
  selector: 'creacion-vuelo',
  templateUrl: './creacion-vuelo.component.html',
  styleUrls: ['./creacion-vuelo.component.css']
})
export class CreacionVueloComponent implements OnInit {

  verCrear:boolean=false;
  verAviones:boolean=false;
  Aereo:any;
  Aviones:any;
  Aereoline:any;
  Aereopuertos:any;
  fecha:Fechas={};
  verAereo:boolean=false;
  constructor(private GestorService:GestorService) { }

  ngOnInit(): void {
    console.log('Areolineas')
         this.getAereolineas();
  }
  async getAereolineas() {
     await this.GestorService.getAereolineas().toPromise().then(res => {
      this.verAereo=true;
      console.log(res)
      this.Aereo=JSON.parse(JSON.stringify(res));
      console.log("Valores" + JSON.stringify(res))
    }).catch((err: any) => {

    });
  }

 
  async getAereopuerto(){
    console.log(this.Aereoline)
    await this.GestorService.getAereopuerto(this.Aereo[this.Aereoline].idAerolinea).toPromise().then(res => {
      this.onVerCrear();
      this.Aereopuertos=res;
    console.log(res)
    }).catch((err: any) => {
    });
    
    for(let i =0;i<this.Aereopuertos.length;i++){
      console.log(this.Aereopuertos[i].nombre)
    }

  }


  async getAviones(HoraLlegada:any,HoraSalida:any,FechaLlegada:any,FechaSalida:any){
    this.fecha.fechaHoraLlegada=new Date(FechaLlegada+'T'+HoraLlegada);
    this.fecha.fechaHoraSalida=new Date(FechaSalida+'T'+HoraSalida);
    await this.GestorService.getAviones(this.Aereo[this.Aereoline].idAerolinea,this.fecha).toPromise().then(res => {
      this.onVerAviones();
      this.Aviones=res;
    console.log(res)
    }).catch((err: any) => {
    });
    
    for(let i =0;i<this.Aviones.length;i++){
      console.log(this.Aviones[i].idAvion)
    }

  }



  onVerCrear(){
    this.verCrear=true;
  }
  onVerAviones(){
    this.verAviones=true;
  }

}
