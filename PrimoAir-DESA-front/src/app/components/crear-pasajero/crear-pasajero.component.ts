import { GestorService } from './../../services/gestor.service';
import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { CrearPasajero } from 'src/app/interfaces/crear-pasajero';
import * as $ from 'jquery';
import Swal from 'sweetalert2'
import { ThisReceiver } from '@angular/compiler';
@Component({
  selector: 'crear-pasajero',
  templateUrl: './crear-pasajero.component.html',
  styleUrls: ['./crear-pasajero.component.css']
})

export class CrearPasajeroComponent implements OnInit {
  verClave: boolean = false;
  verClave1: boolean = false;
  ClaveValida: boolean = false;
  contraTemp: string='';
  Pasajero: CrearPasajero = {};
  paises: any;
  pasaporteVerificado: boolean = false;
  constructor(private gestorService: GestorService) {
    this.Pasajero.paisNacimiento = "Nacionalidad";

  }

  ngOnInit() {
    this.ConsumirCatalogs();


  }

  async ConsumirCatalogs() {
    let Paises = await this.gestorService.getPaises().toPromise().catch((err: any) => {

    });
    console.log("Valores" + JSON.stringify(Paises))
    let data = JSON.parse(JSON.stringify(Paises));
    this.paises = [];
    for (let pais = 0; pais < data.length; pais++) {
      this.paises.push([data[pais]['cca3'], data[pais]['name']['common'], data[pais]['idd']['root'] + data[pais]['idd']['suffixes']])
    }
    this.paises.sort();
    console.log(this.paises)

  }
  async onValidarPasaporte() {

    let pasaporte = <number>this.Pasajero.noPasaporte

    await this.gestorService.VerificarPasaporte(pasaporte).toPromise().then(res => {
      this.pasaporteVerificado = res as boolean
    }
    ).catch((err: any) => {
    });
    this.pasaporteVerificado=! this.pasaporteVerificado
    console.log(JSON.stringify(this.pasaporteVerificado));
  }
  onSubmit() {
    console.log(JSON.stringify(this.Pasajero));

    Swal.fire({
      title: 'Esta seguro de continuar?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si',
      cancelButtonText:'No'
    }).then((result) => {
      if (result.isConfirmed) {
        
   this.RegistrarUsuario()
      }
    })

  }

  async RegistrarUsuario(){
    
    await this.gestorService.RegistrarUsuario(this.Pasajero).toPromise().then(res => {
      console.log('SERVICIO CREAR USUARIO RESPONDIO'+res)
      this.onLimpiar()
      Swal.fire({
        position: 'top-end',
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
  mostrarcontrasena() {
    console.log('ssssss' + $('#contrasena').contents())
    if (!this.verClave) {
      $('#password').attr('type', 'text');
      this.verClave = true;
    } else {
      this.verClave = false;
      $('#password').attr('type', 'password');
    }

  }

  mostrarcontrasena1() {
    if (!this.verClave1) {
      $('#password1').attr('type', 'text');
      this.verClave1 = true;
    } else {
      this.verClave1 = false;
      $('#password1').attr('type', 'password');
    }

  }

  compararClaves(){
    if(this.Pasajero.password==this.contraTemp){
        this.ClaveValida=true;
        console.log('Claves iguales')
    }else{
      this.ClaveValida=false;
      console.log('Claves no iguales')
    }

  }

  onLimpiar(){
    this.Pasajero={}
  }



}
