import { Component, OnInit,Output,EventEmitter } from '@angular/core';
import { GestorService } from 'src/app/services/gestor.service';

import Swal from 'sweetalert2'
@Component({
  selector: 'agregar-pasajero',
  templateUrl: './agregar-pasajero.component.html',
  styleUrls: ['./agregar-pasajero.component.css']
})

export class AgregarPasajeroComponent implements OnInit {

  @Output() Registro = new EventEmitter<boolean>();
  array: any = []
  array2: any = [];
  AsientoSeleccionado: any[] = [];
  Aereopuertos: any;
  Aereoline: any
  verReserva: boolean = false;
  verVuelos: boolean = false;
  Vuelos: any;
  Asientos: any;
  AsientosV: any = [];
  ClaseVuelo: any;
  verClase: boolean = false;
  verAsientos: boolean = false
  Data = {
    aeropuertoLlegada: '',
    aeropuertoSalida: '',
    fechaHoraSalida: ''
  }
  id={
    idVuelo:'',
    noPasaporte:0
  }
  envio={
    claseVuelo:'',
    equipaje:'',
    estadoBoleto:'Pendiente Abordaje',
    sillon:'',
    id:this.id,
    pagoExtra:null,
    pasajero:null,
    pesoEquipaje:null

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
      this.getSillones(vuelo.idsVuelos[a], a)
      console.log('Valor actual' + this.Asientos[a])
    }
    setTimeout(() => {
      let cont = 0;
      let tamAvion;
      for (let i = 0; i < this.Asientos.length; i++) {
        
        for (let z = 0; z < this.Asientos[i].sillones.length; z++) {
          if (this.Asientos[i].tamanio == "pequeño") {
            tamAvion = 4;
          } else {
            tamAvion = 6
          }
          if (cont < tamAvion) {
            this.array.push(this.Asientos[i].sillones[z])
          }
          cont++;
          if (cont == tamAvion) {
            cont = 0;
            this.array2.push(this.array)
            this.array = []
          }
        }
        this.array2.idAvion=this.Asientos[i].idAvion
        this.AsientosV.push(this.array2);
        console.log('Log', this.AsientosV[i])
        this.array2 = []
      }
    }, 500)

  }
  verVuelo(vuelo: any){
    
    Swal.fire({
      title: 'No hay vuelos disponibles en esta fecha',
      icon: 'warning',
      showCancelButton: false,
      confirmButtonColor: '#3085d6'
    })
  }

  setClase(clase: any) {
    console.log(clase)
    this.envio.claseVuelo=clase;
    this.verAsientos = true;
  }
  setMaletas(Maletas: any) {
    console.log(Maletas)
    this.envio.equipaje=Maletas;
    this.verAsientos = true;
  }

  async getSillones(id: any, pos: number) {
    await this.GestorService.getSillones(id).toPromise().then(res => {
      this.Asientos[pos] = res;
      this.Asientos[pos].idAvion=id;
      console.log('Asientos con id ',this.Asientos)

      this.verClase = true;
    }).catch((err: any) => {
    });
  }

  selectAsientos(avion: number, asiento: any) {
    console.log(asiento,this.AsientosV[avion].idAvion)
    this.AsientoSeleccionado[avion] = asiento;
    this.envio.id.idVuelo=this.AsientosV[avion].idAvion;
    this.envio.id.noPasaporte=Number( this.GestorService.pasaporte);
    this.envio.sillon=asiento.idSillon;

    console.log(this.envio)
  }
  async SaveData(){
    await this.GestorService.setPasajeroVuelo(this.GestorService.pasaporte,this.envio).toPromise().then(res => {
      console.log(res)
      Swal.fire({
        title: 'Reserva Completa con Exito',
        icon: 'success',
        showCancelButton: false,
        confirmButtonColor: '#3085d6'
      })
      this.Registro.emit(true);
    }).catch((err: any) => {
      Swal.fire({
        title: 'No se a podido asignar al vuelo',
        icon: 'warning',
        showCancelButton: false,
        confirmButtonColor: '#3085d6'
      })
    });

  }





}
