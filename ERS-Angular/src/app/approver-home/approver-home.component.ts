import {Component, OnInit} from '@angular/core';
import {ApproverService} from './approver.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-approver-home',
  templateUrl: './approver-home.component.html',
  styleUrls: ['./approver-home.component.css']
})
export class ApproverHomeComponent implements OnInit {

  previousApprovedReports: any
  reports: Array<any> // All reports
  bills: any // All Bills
  getHistory: boolean = false;

  constructor(private _approverService: ApproverService,
    private _activatedRoute: ActivatedRoute) {}

  ngOnInit() {


    //    this._activatedRoute.params.subscribe(data => {
    //      this._approverService.getReportsById(data.id)
    //        .subscribe(reports => {
    //          this.reports = reports
    //          console.log(reports)
    //        })
    //    })

    this._approverService.getReportsById(localStorage.getItem("empId"))
      .subscribe(reports => {
        this.reports = reports
        console.log(reports)
      })
    //this._activatedRoute.data.subscribe((reports) => this.reports = reports.reports)

  }
  ngAfterViewInit() {

  }

  toggleGetHistory(){
    this.getHistory = !this.getHistory
  }
  getReportById(id) {
    this._activatedRoute.data.subscribe((reports) => console.log(reports))
  }

  getReportByLevelAndCenter(level, center) {

    this._approverService.getReportsByLevelAndCenter(level, center)
      .subscribe(reports => this.reports = reports)
  }
  getBills(reportid) {
    this._approverService.getBills(reportid)
      .subscribe(bills => this.bills = bills)
  }


  // needs report object, Figure it out the data to send to backend
  updateReport() {
  }

}
