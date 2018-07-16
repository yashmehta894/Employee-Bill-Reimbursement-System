import {ApproverService} from '../approver-home/approver.service';
import {PayrollService} from '../payroll/payroll.service';
import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-history-reports',
  templateUrl: './history-reports.component.html',
  styleUrls: ['./history-reports.component.css']
})

export class HistoryReportsComponent {

  constructor(private _approverService: ApproverService,
    private _payrollService: PayrollService) {}

  reports: any;


  ngDoCheck() {

    // Call in approver service
    if (localStorage.getItem("role") == "approver") {
      // this._approverService.getApprovedReports().subscribe(reports => this.reports = reports)
    }

    //    // call in payroll service
    if (localStorage.getItem("role") == "payroll") {}
    // this._payrollService.getPaidReports().subscribe(reports => this.reports = reports)
  }
  goToSubmitter() {
    return "/submitter/" + localStorage.getItem("empId")
  }


  // ngAfterContentInit(){
  //   alert("routing after content init")
  // }

  // ngAfterContentChecked(){
  //   alert("routing after contect checked")
  // }

  // ngAfterViewInit(){
  //   alert("routing after view init")
  // }

  // ngAfterViewChecked(){
  //   alert("afte view checked")
  // }

}
