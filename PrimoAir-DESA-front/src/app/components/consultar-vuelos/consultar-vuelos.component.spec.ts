import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarVuelosComponent } from './consultar-vuelos.component';

describe('ConsultarVuelosComponent', () => {
  let component: ConsultarVuelosComponent;
  let fixture: ComponentFixture<ConsultarVuelosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarVuelosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultarVuelosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
