import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCatalogComponent } from './list-catalog.component';

describe('ListCatalogComponent', () => {
  let component: ListCatalogComponent;
  let fixture: ComponentFixture<ListCatalogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCatalogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListCatalogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
