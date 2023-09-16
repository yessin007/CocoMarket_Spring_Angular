import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WinDialogueComponent } from './win-dialogue.component';

describe('WinDialogueComponent', () => {
  let component: WinDialogueComponent;
  let fixture: ComponentFixture<WinDialogueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WinDialogueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WinDialogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
