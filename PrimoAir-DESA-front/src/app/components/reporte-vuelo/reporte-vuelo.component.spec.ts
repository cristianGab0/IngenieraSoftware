import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteVueloComponent } from './reporte-vuelo.component';

describe('ReporteVueloComponent', () => {
  let component: ReporteVueloComponent;
  let fixture: ComponentFixture<ReporteVueloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReporteVueloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteVueloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
