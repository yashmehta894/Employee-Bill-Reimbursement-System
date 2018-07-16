import {SubmitterService} from '../submitter.service';
import {Component, OnInit, Input} from '@angular/core';

import {ImageResult, ResizeOptions} from 'ng2-imageupload'
import {FileUploader} from 'ng2-file-upload'

@Component({
  selector: 'app-submitter-bills',
  templateUrl: './submitter-bills.component.html',
  styleUrls: ['./submitter-bills.component.css']
})
export class SubmitterBillsComponent implements OnInit {


  billsColumn = ['Bill No', 'Bill Type', 'Bill Amount', 'Bill Attachment', 'Bill Date'];
  @Input()
  bills: Array<any>;
  images: any = []
  src: string = "";


  billCopy: Array<any>[];

  billResubmit: Array<any>[];

  @Input()
  reportId: number;

  @Input()
  reportStatus: String;

  constructor(private submitterService: SubmitterService) {}

  ngOnInit() {
    console.log(this.bills)
    this.billCopy = this.bills
  }

  populateBills() {
    this.submitterService.getBills(this.reportId)
      .subscribe(resp => this.bills = resp.bills)
    console.log(this.bills)

  }
  resizeOptions: ResizeOptions = {
    resizeMaxHeight: 128,
    resizeMaxWidth: 128
  };

  selected(imageResult: ImageResult, bill) {
    this.src = imageResult.resized
      && imageResult.resized.dataURL
      || imageResult.dataURL;


    let newBill = {
      billId: bill.billId,
      billNo: bill.billNo,
      billType: bill.billType,
      billDate: bill.billDate,
      amount: bill.amount,
      path: this.src
    }

    var index = this.bills.indexOf(bill);

    if (index !== -1) {
     this.bills[index] = newBill;
    }
    //this.images=this.src
     console.log(this.src)
  }

  checkEdit() {
    //    console.log("Bill" + this.reportStatus);
    return this.reportStatus !== "APPROVED" &&
      this.reportStatus !== 'PAID' &&
      this.reportStatus !== 'SUBMITTED';
  }

  reSubmit(bill) {

    if (this.billCopy !== this.bills) {


      this.submitterService.reSubmit(this.bills, this.reportStatus, this.reportId)
        .subscribe(resp => {console.log(resp), window.location.reload()})
    }
    //console.log(this.bills)
  }

}

