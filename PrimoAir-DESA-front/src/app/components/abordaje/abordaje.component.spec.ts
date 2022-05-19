import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbordajeComponent } from './abordaje.component';

describe('AbordajeComponent', () => {
  let component: AbordajeComponent;
  let fixture: ComponentFixture<AbordajeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AbordajeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AbordajeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
