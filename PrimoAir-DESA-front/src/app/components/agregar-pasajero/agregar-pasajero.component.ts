import { Component, OnInit } from '@angular/core';
import { GestorService } from 'src/app/services/gestor.service';

import Swal from 'sweetalert2'
@Component({
  selector: 'agregar-pasajero',
  templateUrl: './agregar-pasajero.component.html',
  styleUrls: ['./agregar-pasajero.component.css']
})

export class AgregarPasajeroComponent implements OnInit {

  array : any = []
  array2: any = []
  Aereopuertos: any;
  Aereoline: any
  verReserva: boolean = false;
  verVuelos: boolean = false;
  Vuelos: any;
  Asientos: any;
  ClaseVuelo: any;
  verClase: boolean = false;
  verAsientos: boolean = false
  Data = {
    aeropuertoLlegada: '',
    aeropuertoSalida: '',
    fechaHoraSalida: ''
  }
  constructor(private GestorService: GestorService) { }
  ngOnInit(): void {
    this.getAereopuerto()



  }

  async getAereopuerto() {
    console.log(this.Aereoline)
    await this.GestorService.getAereopuerto('', 2).toPromise().then(res => {
      this.verReserva = true;
      this.Aereopuertos = res;
      console.log(res)
    }).catch((err: any) => {
    });

    for (let i = 0; i < this.Aereopuertos.length; i++) {
      console.log(this.Aereopuertos[i].nombre)
    }

  }

  async getVuelos(aeropuertoLlegada: number, aeropuertoSalida: number, fechaHoraSalida: any) {
    fechaHoraSalida = new Date(fechaHoraSalida + 'T00:00:00.007Z');
    console.log('Validando Aviones')
    for (let i = 0; i < this.Aereopuertos.length; i++) {
      if (i == aeropuertoLlegada) {
        aeropuertoLlegada = this.Aereopuertos[i].idAeropuerto
        console.log(this.Aereopuertos[i].nombre)
        break
      }
    }
    for (let i = 0; i < this.Aereopuertos.length; i++) {
      if (i == aeropuertoSalida) {
        aeropuertoSalida = this.Aereopuertos[i].idAeropuerto
        console.log(this.Aereopuertos[i].idAeropuerto)
        break
      }
    }
    let Data = {
      aeropuertoLlegada,
      aeropuertoSalida,
      fechaHoraSalida
    }

    await this.GestorService.getVuelos(Data).toPromise().then(res => {
      this.Vuelos = res;
      if (this.Vuelos.length > 0) {
        this.verVuelos = true;
      } else {
        Swal.fire({
          title: 'No hay vuelos disponibles en esta fecha',
          icon: 'warning',
          showCancelButton: false,
          confirmButtonColor: '#3085d6'
        })

      }
      console.log(res)
    }).catch((err: any) => {
    });
  }

  setVuelo(vuelo: any) {
    console.log(vuelo)
    this.Asientos = []
    for (let a = 0; a < vuelo.idsVuelos.length; a++) {
      console.log('ids' + vuelo.idsVuelos[a])
      this.getSillones(vuelo.idsVuelos[a],a)
      console.log('Valor actual'+this.Asientos[a])
    }
    setTimeout(() => {
      let cont=0;
      for (let i = 0; i < this.Asientos.length; i++) {
        console.log('Log',this.Asientos[i])
        for(let z=0;z<this.Asientos[i].length;z++){
          if(cont==4){
            cont=0;
            this.array2.push(this.array)
            this.array=[]
          }
          if(cont<4){
            this.Asientos[i][z].estado=true;
            this.array.push(this.Asientos[i][z])
          }
          cont++;
        }
      }
    }, 2000)

    this.verClase = true;
  }

  setClase(clase: any) {
    console.log(clase)
    this.verAsientos = true;
  }

  async getSillones(id: any,pos:number) {
    await this.GestorService.getSillones(id).toPromise().then(res => {
      this.Asientos[pos]=res;
     
    }).catch((err: any) => {
    });
  }



}
