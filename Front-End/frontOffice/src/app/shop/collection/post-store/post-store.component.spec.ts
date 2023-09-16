import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostStoreComponent } from './post-store.component';

describe('PostStoreComponent', () => {
  let component: PostStoreComponent;
  let fixture: ComponentFixture<PostStoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostStoreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
