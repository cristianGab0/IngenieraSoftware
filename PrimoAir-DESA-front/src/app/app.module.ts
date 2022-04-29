import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { GestorComponentComponent } from './components/gestor-component/gestor-component.component';
import { CrearPasajeroComponent } from './components/crear-pasajero/crear-pasajero.component';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CreacionVueloComponent } from './components/creacion-vuelo/creacion-vuelo.component';
import { AgregarPasajeroComponent } from './components/agregar-pasajero/agregar-pasajero.component';
import { ReporteVueloComponent } from './components/reporte-vuelo/reporte-vuelo.component';
import { ConsultarVuelosComponent } from './components/consultar-vuelos/consultar-vuelos.component';
import { ConsultarAerolineasComponent } from './components/consultar-aerolineas/consultar-aerolineas.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    GestorComponentComponent,
    CrearPasajeroComponent,
    CreacionVueloComponent,
    AgregarPasajeroComponent,
    ReporteVueloComponent,
    ConsultarVuelosComponent,
    ConsultarAerolineasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
