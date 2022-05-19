import { Component, OnInit } from '@angular/core';
import { GestorService } from 'src/app/services/gestor.service';
@Component({
  selector: 'gestor-component',
  templateUrl: './gestor-component.component.html',
  styleUrls: ['./gestor-component.component.css']
})
export class GestorComponentComponent implements OnInit {
  verLogin:boolean=true;
  verCrearPasajero=false;
  verCrearVuelo=false;
  verCrearTripu=false;
  verReserva=false;
  verReporteVuelo=false;
  verConsultarVuelos=false;
  verConsultarAerolineas=false;
  verConsultarAviones=false;
  verPasajerosVuelo =false;
  verDestinos=false;
  verEquipaje=false;
  verAbordaje=false;
  ROLE_ADMIN_AEROLINEA=false;
  ROLE_CLIENTE=false;
  ROLE_ADMIN_ABORDAJE=false;
  ROLE_CONSULTA_AEROLINEA=false;

  

  constructor(public GestorService: GestorService) { }

  ngOnInit(): void {

  }

  OnVerCrearPasajero(){
    this.verLogin=false;
    this.verCrearVuelo=false;
    this.verCrearPasajero=true;
    this.verReserva=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerCrearVuelo(){
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=true;
    this.verReserva=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerReserva(){
    this.verReserva=true;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerCrearTripu(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=true;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerReporteVuelo(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=true;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerConsultarVuelos(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=true;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerConsultarAerolineas(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=true;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerConsultarAviones(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=true;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;
  }
  OnVerConsultarPasajerosVuelo(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=true;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=false;

  }
  onVerDestinos(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=true;
    this.verEquipaje=false;
    this.verAbordaje=false;

  }

  onVerEquipajeVuelo(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=true;
    this.verAbordaje=false;


  }
  onVerAbordaje(){
    this.verReserva=false;
    this.verLogin=false;
    this.verCrearPasajero=false;
    this.verCrearVuelo=false;
    this.verCrearTripu=false;
    this.verReporteVuelo=false;
    this.verConsultarVuelos=false;
    this.verConsultarAerolineas=false;
    this.verConsultarAviones=false;
    this.verPasajerosVuelo=false;
    this.verDestinos=false;
    this.verEquipaje=false;
    this.verAbordaje=true;
  }
  ocultarAgregarPasajero(){
    this.verReserva=false;
  }

  VerificarRoles(){
    this.ROLE_ADMIN_AEROLINEA=this.GestorService.rolPermitied('ROLE_ADMIN_AEROLINEA');
    this.ROLE_CLIENTE=this.GestorService.rolPermitied('ROLE_CLIENTE');
    this.ROLE_ADMIN_ABORDAJE=this.GestorService.rolPermitied('ROLE_ADMIN_ABORDAJE');
    this.ROLE_CONSULTA_AEROLINEA=this.GestorService.rolPermitied('ROLE_CONSULTA_AEROLINEA');
    this.verLogin=false;
  }



}
