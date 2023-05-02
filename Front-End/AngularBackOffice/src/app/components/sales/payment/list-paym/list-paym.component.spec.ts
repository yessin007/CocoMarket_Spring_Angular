import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPaymComponent } from './list-paym.component';

describe('ListPaymComponent', () => {
  let component: ListPaymComponent;
  let fixture: ComponentFixture<ListPaymComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListPaymComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListPaymComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
