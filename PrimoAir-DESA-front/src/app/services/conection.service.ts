import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { CrearPasajero } from 'src/app/interfaces/crear-pasajero';
@Injectable({
  providedIn: 'root'
})
export class ConectionService {

  constructor(private httpClient:HttpClient) { }
  httpOptions = {};
  urlCrear='https://ms-aeropuerto-primos.herokuapp.com/crearPasajero';
  urlConsultaPaises='https://restcountries.com/v3.1/all';
  urlVerificaPasaporte='https://ms-aeropuerto-primos.herokuapp.com/existePasaporte/';
  urlAereolineasDispo='https://ms-aeropuerto-primos.herokuapp.com/obtenerAerolineas';
  urlAvionesDisponibles='https://ms-aeropuerto-primos.herokuapp.com/hayAvionesDisponiblesByAerolinea/';
  urlavionesHora='https://ms-aeropuerto-primos.herokuapp.com/obtenerAvionesPorFechaHora/';
  urlObtenerTripu='https://ms-aeropuerto-primos.herokuapp.com/obtenerTripulantesPorFechaHora';
  urlCrearVuelo='https://ms-aeropuerto-primos.herokuapp.com/crearVuelo';
  getPaises(){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlConsultaPaises, HTTPOptions);
  }
  VerificarPasaporte(pasaporte:number){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<boolean>(this.urlVerificaPasaporte+pasaporte);
  }

  RegistrarUsuario(pasajero:CrearPasajero){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post(this.urlCrear,pasajero ,this.httpOptions);
 
  }

  getAereolineas(){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlAereolineasDispo, HTTPOptions);
  }
  getAviones(Aereolinea:any,data:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post<string>(this.urlavionesHora+Aereolinea,data, HTTPOptions);
  }

  getTripulantes(data:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post<string>(this.urlObtenerTripu,data, HTTPOptions);
  }
  getAereopuerto(Aereolinea:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlAvionesDisponibles+Aereolinea, this.httpOptions);
  }

  registrarVuelo(vuelo:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    console.log('Lanzando peticion')
    return this.httpClient.post(this.urlCrearVuelo,vuelo,this.httpOptions);
  }


}
