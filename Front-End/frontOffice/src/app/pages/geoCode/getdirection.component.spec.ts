import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetdirectionComponent } from './getdirection.component';

describe('GetdirectionComponent', () => {
  let component: GetdirectionComponent;
  let fixture: ComponentFixture<GetdirectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetdirectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetdirectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
