import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitterReportListComponent } from './submitter-report-list.component';

describe('SubmitterReportListComponent', () => {
  let component: SubmitterReportListComponent;
  let fixture: ComponentFixture<SubmitterReportListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubmitterReportListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmitterReportListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
