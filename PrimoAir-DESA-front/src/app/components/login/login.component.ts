import { Component, OnInit, Output,EventEmitter} from '@angular/core';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @Output() Registro = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  OnRegistro(){
    this.Registro.emit(true);
  }

}
