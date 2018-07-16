import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitterBillsComponent } from './submitter-bills.component';

describe('SubmitterBillsComponent', () => {
  let component: SubmitterBillsComponent;
  let fixture: ComponentFixture<SubmitterBillsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubmitterBillsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmitterBillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
