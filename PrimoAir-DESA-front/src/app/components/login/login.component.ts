import { Component, OnInit, Output,EventEmitter} from '@angular/core';
import { GestorService } from 'src/app/services/gestor.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Output() Registro = new EventEmitter<boolean>();
  @Output() Validar = new EventEmitter<boolean>();

  constructor(private GestorService: GestorService) { }

  ngOnInit(): void {
  }

  OnRegistro(){
    this.Registro.emit(true);
  }
  getRol(usuario:any,clave:any){
    console.log(usuario,clave)
   
    let envio={
      password:clave,
      usuario:usuario
    }
    this.onLogear(envio,usuario);
  }

  async onLogear(data:any,pasaporte:any){
    await this.GestorService.getRol(data).toPromise().then(res => {
      this.GestorService.roles=res
      console.log(this.GestorService.roles) 
      this.GestorService.pasaporte=pasaporte;
      Swal.fire({
        text: 'Inicio de Sesion correcto',
        icon: 'success',
        showCancelButton: false,
        confirmButtonColor: '#3085d6'
      })
      this.Validar.emit(true);
    }).catch((err: any) => {
      Swal.fire({
        text: 'Usuario o contraseña incorrecta',
        icon: 'warning',
        showCancelButton: false,
        confirmButtonColor: '#3085d6'
      })
    });
    
  }
}
