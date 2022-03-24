import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class ConectionService {

  constructor(private httpClient:HttpClient) { }
  httpOptions = {}; 
  urlConsultaPaises='https://restcountries.com/v3.1/all'
  getPaises(){
    let HTTPOptions: Object = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      responseType: 'json',
    };
    return this.httpClient.get<string>(this.urlConsultaPaises, HTTPOptions);
  }
}
