import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTrainingDialogComponent } from './new-training-dialog.component';

describe('NewTrainingDialogComponent', () => {
  let component: NewTrainingDialogComponent;
  let fixture: ComponentFixture<NewTrainingDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewTrainingDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewTrainingDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
