import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCatlComponent } from './list-catl.component';

describe('ListCatlComponent', () => {
  let component: ListCatlComponent;
  let fixture: ComponentFixture<ListCatlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCatlComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListCatlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
