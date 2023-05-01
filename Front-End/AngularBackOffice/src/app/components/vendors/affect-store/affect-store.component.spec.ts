import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AffectStoreComponent } from './affect-store.component';

describe('AffectStoreComponent', () => {
  let component: AffectStoreComponent;
  let fixture: ComponentFixture<AffectStoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AffectStoreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AffectStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
