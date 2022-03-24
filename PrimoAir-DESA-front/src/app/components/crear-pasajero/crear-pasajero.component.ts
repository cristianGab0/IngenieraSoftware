import { GestorService } from './../../services/gestor.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
@Component({
  selector: 'crear-pasajero',
  templateUrl: './crear-pasajero.component.html',
  styleUrls: ['./crear-pasajero.component.css']
})
export class CrearPasajeroComponent implements OnInit {

  paises:any;
  constructor(private gestorService:GestorService) { 


  }

  ngOnInit(){
   this.ConsumirCatalogs();
   

  }

  async ConsumirCatalogs(){
    let Paises = await this.gestorService.getPaises().toPromise().catch((err: any) => {
    });
    console.log("Valores"+JSON.stringify(Paises) )
      let data=JSON.parse(JSON.stringify( Paises));
      this.paises=[];
    for(let pais=0;pais<data.length;pais++){
      this.paises.push([data[pais]['cca3'],data[pais]['name']['common'],data[pais]['idd']['root']+data[pais]['idd']['suffixes']])
    }
    this.paises.sort();
    console.log(this.paises)
  
  }

}
