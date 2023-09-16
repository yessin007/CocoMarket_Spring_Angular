import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopCollectionComponent } from './shop-collection.component';

describe('ShopCollectionComponent', () => {
  let component: ShopCollectionComponent;
  let fixture: ComponentFixture<ShopCollectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopCollectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShopCollectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
