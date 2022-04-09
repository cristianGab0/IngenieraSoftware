import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreacionVueloComponent } from './creacion-vuelo.component';

describe('CreacionVueloComponent', () => {
  let component: CreacionVueloComponent;
  let fixture: ComponentFixture<CreacionVueloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreacionVueloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreacionVueloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
