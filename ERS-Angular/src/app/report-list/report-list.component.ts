import {ApproverService} from '../approver-home/approver.service';
import {Component, OnInit, Input} from '@angular/core';
import {NgForm} from '@angular/forms';
//import {ReportService} from '../report.service'
import {Router} from '@angular/router';

@Component({
  selector: 'app-report-list',
  templateUrl: './report-list.component.html',
  styleUrls: ['./report-list.component.css']
})
export class ReportListComponent implements OnInit {
  columns = []

  @Input()
  reports: Array<any>;

  editButtonClicked: boolean = false;

  detailsButton = "Details"

  bills: Array<any>[];

  currentReportId: number;

  comment: any;

  currentReportStatus: String;

  viewBills: boolean = false;

  report: any;

  levels = []

  approverLevel = +localStorage.getItem("level");

  costCenter = localStorage.getItem("costCenterName")
  //level = this.approverLevel

  constructor(private _approverService: ApproverService,
    private _router: Router) {}

  ngOnInit() {
    //let level = 4;
    for (let i = 1; i <= this.approverLevel; i++) {
      this.levels.push(i)
    }
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
    console.log(this.report)
    this._approverService.updateStatus(this.report)
    window.location.reload()
  }

  getReportByLevelAndCostCenter(form: NgForm) {
    //    alert(form.value.level)
    this.viewBills = false;
    this._approverService.getReportsByLevelAndCenter(form.value.level, 1)
      .subscribe(reports => this.reports = reports)
  }

  hideBillDetails() {
    this.viewBills = false
  }

  goToSubmitter() {
    return "/submitter/" + localStorage.getItem("empId")
  }


}
