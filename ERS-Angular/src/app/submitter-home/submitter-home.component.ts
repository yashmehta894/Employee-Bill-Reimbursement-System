import {SharedService} from '../shared.service';
import {SubmitterService} from '../submitter.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-submitter-home',
  templateUrl: './submitter-home.component.html',
  styleUrls: ['./submitter-home.component.css']
})
export class SubmitterHomeComponent implements OnInit {


  //empId = 1
  submittedReports: any[] = []
  approvedReports: any[] = []
  savedReports: any[] = []
  rejectedReports: any[] = []
  paidReports: any[] = []
  isSubmittedReport = true
  isApprovedReport = false
  isSavedReport = false
  isRejectedReport = false
  isPaidReport = false


  constructor(private submitterService: SubmitterService,
    private _activatedRoute: ActivatedRoute,
    private _shaedService: SharedService) {}

  ngOnInit() {

    this._shaedService.newReport.subscribe(data => this.submittedReports.push(data))

    this._activatedRoute.params.subscribe(data => {
      this.submitterService.getReportByEmployeeId(data.id)
        .subscribe(response => {
          this.submittedReports = response.filter(item => item.status === 'SUBMITTED')
          console.log(this.submittedReports)

        }),
        this.submitterService.getReportByEmployeeId(data.id)
          .subscribe(response => {
            this.approvedReports = response.filter(item => item.status === 'APPROVED')
            console.log(this.approvedReports)
          }),
        this.submitterService.getReportByEmployeeId(data.id)
          .subscribe(response => {
            this.rejectedReports = response.filter(item => item.status == 'APPROVER_REJECTED'
              || item.status == 'PAYROLL_REJECTED')

            console.log(this.rejectedReports)

          }),
        this.submitterService.getReportByEmployeeId(data.id)
          .subscribe(response => {
            this.paidReports = response.filter(item => item.status === 'PAID')
            console.log(this.paidReports)
          })


    })
  }
  onReportClick(event) {

    if (event == 'submitReports') {
      this.isSubmittedReport = true
      this.isApprovedReport = false
      this.isRejectedReport = false
      this.isSavedReport = false
      this.isPaidReport = false

    } else if (event == 'rejectedReports') {
      this.isSubmittedReport = false
      this.isApprovedReport = false
      this.isRejectedReport = true
      this.isSavedReport = false
      this.isPaidReport = false

    } else if (event == 'paidReports') {
      this.isSubmittedReport = false
      this.isApprovedReport = false
      this.isRejectedReport = false
      this.isSavedReport = false
      this.isPaidReport = true

    } else if (event == 'approvedReports') {
      this.isSubmittedReport = false
      this.isApprovedReport = true
      this.isRejectedReport = false
      this.isSavedReport = false
      this.isPaidReport = false

    } else if (event == 'savedReports') {
      this.isSubmittedReport = false
      this.isApprovedReport = false
      this.isRejectedReport = false
      this.isSavedReport = true
      this.isPaidReport = false

    }

  }



}
