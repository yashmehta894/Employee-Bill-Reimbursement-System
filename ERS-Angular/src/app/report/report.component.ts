import {ApproverService} from '../approver-home/approver.service';
import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
//import {ReportService} from '../report.service'

@Component({
  selector: 'report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  data: any;

  @Input()
  report: any;

  id: number = this.report;

  billsColumn = ['Bill No', 'Bill Type', 'Bill Id', 'Bill Amount', 'Bill Attachment', 'Bill Date'];

  bills: Array<any>[];

  expand: boolean = false;
  edit: boolean = false;
  editButton: boolean = false;

  @Output()
  onExpand = new EventEmitter()

  constructor(private _approverService: ApproverService) {}

  ngOnInit() {
    //console.log(this.report.ReportId)
    this.editButton = this.report.Status != "ACCEPTED";
  }

  loadBills(id: number) {
    this._approverService.getBills(id)
      .subscribe(resp => this.bills = resp.bills)
  }
  toggleExpand(id) {
    this.expand = !this.expand
    this.loadBills(id);
  }

  makeEdit(event) {
    event.preventDefault();
    console.log("edit");
    this.edit = true;
  }
}
