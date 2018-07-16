import {SubmitterService} from '../submitter.service';
import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-submitter-report-list',
  templateUrl: './submitter-report-list.component.html',
  styleUrls: ['./submitter-report-list.component.css']
})
export class SubmitterReportListComponent implements OnInit {

  columns = []

  @Input()
  submittedReports: Array<any>;
  editButtonClicked: boolean = false;

  bills: Array<any>[];
  currentReportId: number;

  currentReportStatus: String;
  viewBills: boolean = false;

  constructor(private submitterService: SubmitterService) {}

  ngOnInit() {
    console.log(this.submittedReports + "submittedReports")
  }

  Billdetails(id, status) {
    // console.log(id);
    // console.log(status);
    this.viewBills = true;
    this.currentReportId = id;
    this.currentReportStatus = status;

    console.log(this.currentReportId + " CURRENT ID" + status)

    this.submitterService.getBills(this.currentReportId)
      .subscribe(resp => this.bills = resp)
    console.log(this.bills)
  }


}
