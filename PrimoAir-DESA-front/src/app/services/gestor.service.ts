import { ConectionService } from './conection.service';
import { Injectable } from '@angular/core';
import { CrearPasajero } from '../interfaces/crear-pasajero';
@Injectable({
  providedIn: 'root'
})
export class GestorService {

  constructor(private ConectionService: ConectionService) { }
  getPaises() {
    return this.ConectionService.getPaises();
  }
  VerificarPasaporte(pasaporte: number) {
    return this.ConectionService.VerificarPasaporte(pasaporte);
  }

  RegistrarUsuario(pasajero: CrearPasajero) {
    return this.ConectionService.RegistrarUsuario(pasajero);
  }
  getAereolineas() {
    return this.ConectionService.getAereolineas();
  }

  getAviones(Aereolinea: any, data: any) {
    return this.ConectionService.getAviones(Aereolinea, data);
  }
  getTripulantes(data: any) {
    return this.ConectionService.getTripulantes(data);
  }
  getAereopuerto(Aereolinea: any, Tipo: any) {
    return this.ConectionService.getAereopuerto(Aereolinea, Tipo);
  }


  registrarVuelo(vuelo: any) {
    console.log(JSON.stringify(vuelo))
    return this.ConectionService.registrarVuelo(vuelo);
  }
  getVuelos(data: any) {
    return this.ConectionService.getVuelos(data);
  }
  getSillones(id: any) {
    return this.ConectionService.getSillones(id);
  }

  getDetalleVuelo(id: any) {
    return this.ConectionService.getDetalleVuelo(id);
  }
  getReporteVuelo(id: any) {
    return this.ConectionService.getReporteVuelo(id);
  }
  
  getConsultarVuelos(data:any){
    return this.ConectionService.getConsultarVuelos(data);
  }
  getAereolineasAere(id:any){
    return this.ConectionService.getAereolineasAere(id);
  }
  getDetalleAvion(id:any){
    return this.ConectionService.getDetalleAvion(id);

  }

  getPasajeroVuelo(id:any){
    return this.ConectionService.getPasajeroVuelo(id);

  }
  getDestinosAutorizados(id:any){
    return this.ConectionService.getDestinosAutorizados(id);
  }

}
