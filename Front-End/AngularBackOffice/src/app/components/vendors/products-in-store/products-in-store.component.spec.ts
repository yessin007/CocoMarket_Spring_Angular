import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsInStoreComponent } from './products-in-store.component';

describe('ProductsInStoreComponent', () => {
  let component: ProductsInStoreComponent;
  let fixture: ComponentFixture<ProductsInStoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductsInStoreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsInStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
