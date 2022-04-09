import { ConectionService } from './conection.service';
import { Injectable } from '@angular/core';
import { CrearPasajero } from '../interfaces/crear-pasajero';
@Injectable({
  providedIn: 'root'
})
export class GestorService {

  constructor(private ConectionService:ConectionService) { }
  getPaises(){
    return this.ConectionService.getPaises();
  }
  VerificarPasaporte(pasaporte:number){
    return this.ConectionService.VerificarPasaporte(pasaporte);
  }

  RegistrarUsuario(pasajero:CrearPasajero){
    return this.ConectionService.RegistrarUsuario(pasajero);
  }
  getAereolineas(){
    return this.ConectionService.getAereolineas();
  }

  getAviones(Aereolinea:any,data:any){
    return this.ConectionService.getAviones(Aereolinea,data);
  }
  getAereopuerto(Aereolinea:any){
    return this.ConectionService.getAereopuerto(Aereolinea);
  }
}
