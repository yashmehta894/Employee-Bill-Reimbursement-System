import { Component, OnInit, Input } from '@angular/core';
//import {ReportService} from '../report.service'

@Component({
  selector: 'bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {

  billsColumn = ['Bill No','Bill Type','Bill Id','Bill Amount','Bill Attachment','Bill Date'];
  
  @Input()
  bills: Array<any>[];

  @Input()
  reportId:number;

  @Input()
  reportStatus:String;

  constructor() {}

  ngOnInit() {
   
  }

//populateBills() {   
//    this.reportService.loadBills(this.reportId)
//      .subscribe(resp => this.bills=resp.bills)
//    // console.log(this.bills)
//  }

  checkEdit(){
    // console.log("Bill"+ this.reportStatus);   
    return this.reportStatus !=="ACCEPTED";
  }
  
}
