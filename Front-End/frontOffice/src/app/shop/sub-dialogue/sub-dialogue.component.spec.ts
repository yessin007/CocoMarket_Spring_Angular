import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubDialogueComponent } from './sub-dialogue.component';

describe('SubDialogueComponent', () => {
  let component: SubDialogueComponent;
  let fixture: ComponentFixture<SubDialogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubDialogueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
