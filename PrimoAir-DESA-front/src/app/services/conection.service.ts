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
  urlAereopuertosFull='https://ms-aeropuerto-primos.herokuapp.com/obtenerAeropuertos';
  urlVuelos='https://ms-aeropuerto-primos.herokuapp.com/obtenerVuelosByAeropuertosFechas';
  urlSillones='https://ms-aeropuerto-primos.herokuapp.com/obtenerSillonesDiponiblesByVuelo/';
  urlDetalleVuelo='https://ms-aeropuerto-primos.herokuapp.com/obtenerDetalleVuelos';
  urlReporteVuelo='https://ms-aeropuerto-primos.herokuapp.com/reporte/detalleVuelo/';
  urlConsultarVuelos='https://ms-aeropuerto-primos.herokuapp.com/reporte/vuelosPorFechaHora';
  urlAerolineasAero='https://ms-aeropuerto-primos.herokuapp.com/reporte/obtenerAerolineaByAeropuertoAutorizado/';
  urlDetalleAvion='https://ms-aeropuerto-primos.herokuapp.com/reporte/obtenerDetalleAvionesPorAerolinea/';
  urlPasajerosVuelo='https://ms-aeropuerto-primos.herokuapp.com/reporte/obtenerPasajerosPorVuelo/';
  urlDestinosAutorizados='https://ms-aeropuerto-primos.herokuapp.com/reporte/obtenerAeropuertosAutorizadosPorAerolinea/';
  urlEquipaje='https://ms-aeropuerto-primos.herokuapp.com/reporte/obtenerDetalleEquipajeVuelo/';
  urlLogin='https://ms-aeropuerto-primos.herokuapp.com/rolesByLogin';
  urlAsignarPasajero='https://ms-aeropuerto-primos.herokuapp.com/agregarPasajeroVuelo/';
  urlObtenerVuelosAbordar='https://ms-aeropuerto-primos.herokuapp.com/obtenerVuelosAbordar/';
  urlVerificarVueloAbordar='https://ms-aeropuerto-primos.herokuapp.com/validarPasaporteVuelo/';
  urlAbordar='https://ms-aeropuerto-primos.herokuapp.com/abordarPasajero/';
  urlFinalizarAbordaje='https://ms-aeropuerto-primos.herokuapp.com/finalizarAbordaje/';
  urlGetNombre='https://ms-aeropuerto-primos.herokuapp.com/obtenerNombrePasajero/'
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
  getAereopuerto(Aereolinea:any,tipo:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    if(tipo==1){
    return this.httpClient.get<string>(this.urlAvionesDisponibles+Aereolinea, this.httpOptions);
  }else{
    return this.httpClient.get<string>(this.urlAereopuertosFull, this.httpOptions);
  }
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

  getVuelos(data:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post<string>(this.urlVuelos,data, HTTPOptions);
  }

  getSillones(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlSillones+id, HTTPOptions);
  }
  getDetalleVuelo(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post<string>(this.urlDetalleVuelo,id, HTTPOptions);
  }
  getReporteVuelo(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlReporteVuelo+id, HTTPOptions);
  }
  getConsultarVuelos(data:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post<string>(this.urlReporteVuelo,data, HTTPOptions);
  }

  getAereolineasAere(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlAerolineasAero+id, HTTPOptions);
  }

  getDetalleAvion(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlDetalleAvion+id, HTTPOptions);
  }
  getPasajeroVuelo(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlPasajerosVuelo+id, HTTPOptions);
  }
  getDestinosAutorizados(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlDestinosAutorizados+id, HTTPOptions);
  }
  getDetalleEquipaje(id:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlEquipaje+id, HTTPOptions);
  }

  getRol(data:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post<string>(this.urlLogin,data,HTTPOptions);
  }

  setPasajeroVuelo(pasaporte:any,data:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.post<string>(this.urlAsignarPasajero,[data], HTTPOptions);
  }

  getVuelosAbordaje(){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlObtenerVuelosAbordar, HTTPOptions);
  }
  getVueloValidoAbordar(data:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlVerificarVueloAbordar+data[0]+'/'+data[1], HTTPOptions);
  }
  setAbordarVuelo(data:any){

    return this.httpClient.put<string>(this.urlAbordar+data[0]+'/'+data[1],{});
  }
  setFinalizarAbordaje(data:any){

    return this.httpClient.put<string>(this.urlFinalizarAbordaje+data,{});
  }
  getNombre(pasaporte:any){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/text',
      }),
      responseType: 'text',
    };
    return this.httpClient.get<string>(this.urlGetNombre+pasaporte, HTTPOptions);
  }


  

}
