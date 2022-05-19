import  Swal from 'sweetalert2';
import { Component, OnInit } from '@angular/core';
import {GestorService } from 'src/app/services/gestor.service';
@Component({
  selector: 'abordaje',
  templateUrl: './abordaje.component.html',
  styleUrls: ['./abordaje.component.css']
})
export class AbordajeComponent implements OnInit {
  verVuelos=false;
  Vuelos:any;
  verOpciones=false;
  pasaporte:any;
  verBusqueda=false;
  verAbordar:any='';
  vueloSeleccionado='';
  constructor(private GestorService: GestorService) { }

  ngOnInit(): void {
    this.getVuelos();
  }


  async getVuelos() {
    await this.GestorService.getVuelosAbordaje().toPromise().then(res => {
      this.Vuelos = res;
      this.verVuelos=true;
      console.log(res)
    }).catch((err: any) => {
    });
  }

  selector(position:any){
    console.log('Vuelo Seleccionado',this.Vuelos[position])
    this.vueloSeleccionado=this.Vuelos[position].idVuelo;
    this.verVuelos=false;
    this.verOpciones=true;
  }
  nuevo(){
    this.verVuelos=true;
    this.verOpciones=false;
    this.verBusqueda=false;
    this.vueloSeleccionado='';
  }
  enableBusqueda(){
    this.verBusqueda=true;
  }

  async validarVuelos(pasaporte:any) {
    await this.GestorService.getVueloValidoAbordar([pasaporte,this.vueloSeleccionado]).toPromise().then(res => {
      if(res) {
        this.verAbordar=true;
        this.pasaporte=pasaporte;
      }else {
        this.verAbordar=false;
        this.pasaporte='';
        Swal.fire({
          text: 'El pasajero no se encuentra registrado en el vuelo',
          icon: 'warning',
          showCancelButton: false,
          confirmButtonColor: '#3085d6'
        })
      }
      console.log(res)
    }).catch((err: any) => {
    });
  }

  async AbordarVuelo() {
    await this.GestorService.setAbordarVuelo([this.pasaporte,this.vueloSeleccionado]).toPromise().then(res => {
   
        this.verAbordar=false;
        
        Swal.fire({
          text: 'El pasajero fue asignado exitosamente',
          icon: 'success',
          showCancelButton: false,
          showConfirmButton: true,
          confirmButtonText:'Aceptar',
          confirmButtonColor: '#3085d6'
        })
        this.nuevo()
      
      console.log(res)
    }).catch((err: any) => {
    });
  }
  async finalizarAbordaje() {
    await this.GestorService.setFinalizarAbordaje(this.vueloSeleccionado).toPromise().then(res => { 
        Swal.fire({
          text: 'Finalizo el Abordaje',
          icon: 'success',
          showCancelButton: false,
          showConfirmButton: true,
          confirmButtonText:'Aceptar',
          confirmButtonColor: '#3085d6'
        })
        this.nuevo()
      
      console.log(res)
    }).catch((err: any) => {
    });
  }


}
