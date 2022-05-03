import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarPasajerosVueloComponent } from './consultar-pasajeros-vuelo.component';

describe('ConsultarPasajerosVueloComponent', () => {
  let component: ConsultarPasajerosVueloComponent;
  let fixture: ComponentFixture<ConsultarPasajerosVueloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarPasajerosVueloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarPasajerosVueloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
