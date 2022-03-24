import { ConectionService } from './conection.service';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class GestorService {

  constructor(private ConectionService:ConectionService) { }
  getPaises(){
    return this.ConectionService.getPaises();
  }
}
