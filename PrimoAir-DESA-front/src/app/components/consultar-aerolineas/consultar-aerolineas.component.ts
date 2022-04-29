import { Component, OnInit } from '@angular/core';
import { GestorService } from 'src/app/services/gestor.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'consultar-aerolineas',
  templateUrl: './consultar-aerolineas.component.html',
  styleUrls: ['./consultar-aerolineas.component.css']
})
export class ConsultarAerolineasComponent implements OnInit {

  constructor(private GestorService: GestorService) { }
  verDatos=false;
  cargados=false;
  Datos1:any;
  Datos:any;
  n:any;
  ngOnInit(): void {
    this.getAereopuerto();
  }


  onReset(){
    this.verDatos=false;
    this.n='';
  }
  async getAereopuerto() {
    await this.GestorService.getAereopuerto('', 2).toPromise().then(res => {
      this.cargados = true;
      this.Datos1 = res;
      console.log(res)
    }).catch((err: any) => {
    });


  }

  async onConsultarAereolineas(id:any){
  
    for (let i = 0; i < this.Datos1.length; i++) {
      if(i==id){
        id=this.Datos1[i].idAeropuerto;
      console.log(this.Datos1[i].nombre);
      break;
      }
    }

    await this.GestorService.getAereolineasAere(id).toPromise().then(res => {
      this.Datos=res;
      this.verDatos = true;
    }).catch((err: any) => {
      Swal.fire({
        text: err,
        icon: 'warning',
        showCancelButton: false,
        confirmButtonColor: '#3085d6'
      })
    });
    
  }
  

}
