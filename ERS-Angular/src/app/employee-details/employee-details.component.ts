import {SubmitterService} from '../submitter.service';
import {Component, OnInit, ElementRef} from '@angular/core';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {
//  empId = 1
//  empName = "sita"
  totalAmountReimbursed;
  totalRemainingAmt;
  date = "2017-01-01"

  empId: any
  empName: any
  level: any
  mgrId: any

  constructor(private submitterService: SubmitterService,
    private element: ElementRef) {}

  ngOnInit() {

    this.empId = localStorage.getItem('empId');
    this.level = localStorage.getItem('level');
    this.empName = localStorage.getItem('name');
    this.mgrId = localStorage.getItem('mgrId');


    this.totalAmountReimbursed
      = this.submitterService.getTotalAmount(this.empId, this.date)
        .subscribe(response => {
          console.log(response)
          this.totalAmountReimbursed = response

        });
    this.totalAmountReimbursed
      = this.submitterService.getRemainingAmt(this.empId, this.date)
        .subscribe(response => {
          console.log(response)
          this.totalRemainingAmt = response
        });
  }

}
