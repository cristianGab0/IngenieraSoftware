import { GestorService } from './../../services/gestor.service';
import { Component, OnInit } from '@angular/core';
import { Fechas } from 'src/app/interfaces/fechas';
import * as $ from 'jquery';
import { CrearVuelo } from 'src/app/interfaces/crear-avion';
import Swal from 'sweetalert2'
@Component({
  selector: 'creacion-vuelo',
  templateUrl: './creacion-vuelo.component.html',
  styleUrls: ['./creacion-vuelo.component.css']
})
export class CreacionVueloComponent implements OnInit {
  fechaHora: Fechas | undefined;
  verCrear: boolean = false;
  verAviones: boolean = false;
  CrearVueloData: CrearVuelo.CrearVueloData = {};
  Aereo: any;
  Aviones: any;
  Tripulantes: any;
  Aereoline: any;
  Aereopuertos: any;
  fecha: Fechas = {};
  verAereo: boolean = false;
  constructor(private GestorService: GestorService) { }

  ngOnInit(): void {
    console.log('Areolineas')
    this.getAereolineas();
  }
  async getAereolineas() {
    await this.GestorService.getAereolineas().toPromise().then(res => {
      this.verAereo = true;
      console.log(res)
      this.Aereo = JSON.parse(JSON.stringify(res));
      console.log("Valores" + JSON.stringify(res))
    }).catch((err: any) => {

    });
  }


  async getAereopuerto() {
    console.log(this.Aereoline)
    await this.GestorService.getAereopuerto(this.Aereo[this.Aereoline].idAerolinea, 1).toPromise().then(res => {
      this.onVerCrear();
      this.Aereopuertos = res;
      console.log(res)
    }).catch((err: any) => {
    });

    for (let i = 0; i < this.Aereopuertos.length; i++) {
      console.log(this.Aereopuertos[i].nombre)
    }

  }


  async getAviones(AirLlegada: any, AirSalida: any, HoraLlegada: any, HoraSalida: any, FechaLlegada: any, FechaSalida: any) {
    this.fecha.fechaHoraLlegada = new Date(FechaLlegada + 'T' + HoraLlegada);
    this.fecha.fechaHoraSalida = new Date(FechaSalida + 'T' + HoraSalida);
    console.log('Validando Aviones')
    for (let i = 0; i < this.Aereopuertos.length; i++) {
      if (i == AirLlegada) {
        AirLlegada = this.Aereopuertos[i]
        console.log(this.Aereopuertos[i].nombre)
      }
    }
    for (let i = 0; i < this.Aereopuertos.length; i++) {
      if (i == AirSalida) {
        AirSalida = this.Aereopuertos[i]
        console.log(this.Aereopuertos[i].nombre)
      }
    }

    this.CrearVueloData.aeropuertoSalida = AirSalida.idAeropuerto;
    this.CrearVueloData.aeropuertoLlegada = AirLlegada.idAeropuerto;
    this.CrearVueloData.fecha_hora_llegada = new Date(FechaLlegada + 'T' + HoraLlegada);
    this.CrearVueloData.fecha_hora_salida = new Date(FechaSalida + 'T' + HoraSalida);
    console.log('--------' + JSON.stringify(this.CrearVueloData))


    this.getTripulantes();

    await this.GestorService.getAviones(this.Aereo[this.Aereoline].idAerolinea, this.fecha).toPromise().then(res => {
      this.onVerAviones();
      this.Aviones = res;
      console.log(res)
    }).catch((err: any) => {
    });

    for (let i = 0; i < this.Aviones.length; i++) {
      console.log(this.Aviones[i].idAvion)
    }

  }
  async getTripulantes() {
    await this.GestorService.getTripulantes(this.fecha).toPromise().then(res => {
      this.Tripulantes = res;
      console.log(res)
    }).catch((err: any) => {
    });

    for (let i = 0; i < this.Tripulantes.length; i++) {
      console.log(this.Tripulantes[i])
    }

  }


  onVerCrear() {
    this.verCrear = true;
  }
  onVerAviones() {
    this.verAviones = true;
  }

  onSaveAir(Avion: any, Eco: any, Eje: any, Tripu: any) {
    console.log('-----------' + JSON.stringify(Avion))
    for (let i = 0; i < this.Aviones.length; i++) {
      if (i == Avion) {
        console.log(this.Aviones[i])
        Avion = this.Aviones[i];

      }

    }
    for (let a = 0; a < Tripu.length; a++) {
      for (let i = 0; i < this.Tripulantes.length; i++) {
        if (i == Tripu[a]) {
          Tripu[a] = this.Tripulantes[i];
          console.log(this.Tripulantes[i])
        }
      }
    }

    console.log(Eco)
    console.log(Eje)
    console.log(Tripu)

    this.CrearVueloData.avion = {}
    this.CrearVueloData.avion.anio = Avion.anio;
    this.CrearVueloData.avion.estado = Avion.estado;
    this.CrearVueloData.avion.id = {}
    this.CrearVueloData.avion.id.idAerolinea = Avion.idAerolinea;
    this.CrearVueloData.avion.id.idAvion = Avion.idAvion;
    this.CrearVueloData.precioEconomica = Eco;
    this.CrearVueloData.precioEjecutiva = Eje;
    this.CrearVueloData.idvuelo = 0;
    this.CrearVueloData.tripulantes = []
    this.CrearVueloData.tripulantes=Tripu;

    console.log('JSON ENVIAR ' + JSON.stringify(this.CrearVueloData))
    this.registrarVuelo();

  }


  async registrarVuelo() {
    console.log('Registrando vuelo')
    await this.GestorService.registrarVuelo(this.CrearVueloData).toPromise().then(res => {
      console.log('SERVICIO CREAR USUARIO RESPONDIO' + res)
      Swal.fire({
        position: 'top',
        icon: 'success',
        title: 'Se ha creado con éxito el Usuario',
        showConfirmButton: false,
        timer: 1500
      })
    }
    ).catch((err: any) => {
      console.log(err.error);
    });
  }

}
