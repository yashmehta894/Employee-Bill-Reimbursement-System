import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmittersFormComponent } from './submitters-form.component';

describe('SubmittersFormComponent', () => {
  let component: SubmittersFormComponent;
  let fixture: ComponentFixture<SubmittersFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubmittersFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmittersFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
