import {ApproverService} from '../approver-home/approver.service';
import {Component, OnInit} from '@angular/core';
//import {PayrollService} from './payroll.service'
import {PayrollService} from './payroll.service'
import {NgForm} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-payroll',
  templateUrl: './payroll.component.html',
  styleUrls: ['./payroll.component.css']
})
export class PayrollComponent implements OnInit {

  reports: any

  detailsButton = "Details"

  report: any;

  editButtonClicked: boolean = false;

  bills: Array<any>[];

  currentReportId: number;

  comment: any;

  currentReportStatus: String;

  viewBills: boolean = false;


  constructor(private _payrollService: PayrollService,

    private _approverService: ApproverService,

    private _activatedRoute: ActivatedRoute) {}

  ngOnInit() {


    this._activatedRoute.params.subscribe(data => {
      this._payrollService.getAllReports("APPROVED")
        .subscribe(reports => {
          this.reports = reports
        })
    })
  }

  Billdetails(id: number, status: String) {
    console.log(id);
    console.log(status);
    this.viewBills = true;
    this.currentReportId = id;
    this.currentReportStatus = status;


    this._approverService.getBills(this.currentReportId)
      .subscribe(resp => {
        this.bills = resp
        console.log(this.bills)
      })
  }

  updateReport(report, status) {
    //alert(status)
    report.status = status
    this.report = report;

    //window.location.reload()
    //this._router.navigate(["/approver"])
  }

  addComment(form: NgForm) {
    console.log(form.value)
    this.report.comment = form.value.comment
    //alert(this.report.status)
    this._payrollService.updateStatus(this.report)
    window.location.reload()
  }


  hideBillDetails() {
    this.viewBills = false
  }

  goToSubmitter() {
    return "/submitter/" + localStorage.getItem("empId")
  }

}
