import {CostCenter} from '../cost-center';
import {SubmitterService} from '../submitter.service';
import {JsonPipe} from '@angular/common';
import {Component, OnInit, ElementRef} from '@angular/core';
import {NgForm, FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

import {ImageResult,ResizeOptions} from 'ng2-imageupload'
import {FileUploader } from 'ng2-file-upload'

@Component({
  selector: 'app-submitters-form',
  templateUrl: './submitters-form.component.html',
  styleUrls: ['./submitters-form.component.css']
})
export class SubmittersFormComponent implements OnInit {


  empId: any
  empName: any
  level: any
  mgrId: any

  path = '';
  ctrl = new FormControl('');

  images: any = []
  src: string = "";
 public uploader:FileUploader = new FileUploader({url:'http://localhost:8080/upload'});




  totalAmountReimbursed;
  totalRemainingAmt;

  today = new Date()
  
  year = this.today.getFullYear()
  month = this.today.getMonth() + 1;
  day = this.today.getDate()
  finalDate = this.year + "-" + this.month + "-" + this.day
  
  costCenters: Array<CostCenter> = []
  approvers: Array<any> = []

  selectedCostCenter;
  costcenter: CostCenter
  mgrName;
  errorMsg;
  

  bills: any = [];

  billForm: FormGroup


  constructor(private submitterService: SubmitterService,
    private element: ElementRef,
    private formbuilder: FormBuilder,
    private _activatedRoute: ActivatedRoute,
    private router: Router) {}

  ngOnInit() {

    this.empId = localStorage.getItem('empId');
    this.level = localStorage.getItem('level');
    this.empName = localStorage.getItem('name');
    this.mgrId = localStorage.getItem('mgrId');


    this._activatedRoute.params.subscribe((data) => {

      this.ctrl.valueChanges.subscribe((v) => {
        this.path = v;
      });

      this.billForm = this.formbuilder.group({
        billNo: ['', Validators.required],
        billType: ['', Validators.required],
        billDate: ['', Validators.required],
        amount: ['', Validators.required],
        path: ['']
      })

      
      
      this.totalAmountReimbursed
        = this.submitterService.getTotalAmount(this.empId, this.finalDate)
          .subscribe(response => {
            console.log(response)
            this.totalAmountReimbursed = response

          });
      this.totalAmountReimbursed
        = this.submitterService.getRemainingAmt(this.empId, this.finalDate)
          .subscribe(response => {
            console.log(response)
            this.totalRemainingAmt = response
          });


      // this.approvers=this.submitterService.getApproverByLevel()

      this.submitterService.getCostCenters()
        .subscribe(response => {
          console.log(response)
          this.costCenters = response
          console.log(this.costCenters)

        });
      //   this.submitterService.getApproverByLevel(1,0)
      //      .subscribe(response=>{
      //        this.approvers=response
      //        console.log(response+"response")
      //        console.log(this.approvers+"approvers")
      //      })
      this.submitterService.getMgr(this.mgrId)
        .subscribe(response => {
          this.mgrName = response.name
        })


    })
  }
  
  isValidForm(){
    return this.billForm.valid
  }


resizeOptions: ResizeOptions = {
       resizeMaxHeight: 128,
       resizeMaxWidth: 128
   };

   selected(imageResult: ImageResult) {
       this.src = imageResult.resized
           && imageResult.resized.dataURL
           || imageResult.dataURL;
     
     this.images.push(this.src)
     //this.images=this.src
     
     
     
     console.log(this.src)
   }
  
  
  saveBill(event) {


    console.log(this.billForm.valid)
    console.log(this.bills)
    if (this.billForm.valid) {
      
     let newBill={
                  billNo: this.billForm.get('billNo').value,
                  billType: this.billForm.get('billType').value,
                  billDate: this.billForm.get('billDate').value,
                  amount: this.billForm.get('amount').value,
                  path: this.src
     }
    
     this.bills.push(newBill)
     
      console.log(JSON.stringify(this.bills)+"newBill")
     

    } else {

      this.errorMsg = this.billForm.errors
    }

  }
  
 


  onFormSubmit(form: NgForm) {
        
    this.submitterService.addReport(form.value, this.bills, this.finalDate,this.empId, this.images).subscribe(
      res => {console.log(res), this.router.navigateByUrl('/submitter/' + this.empId)})


  }

  select(selectedValue) {

    console.log(selectedValue + "selectedValue")
    this.submitterService.getApproverByLevel(selectedValue, localStorage.getItem("level"))
      .subscribe(response => {
        this.approvers = response
        //this.approvers = this.approvers.filter(item => item.name !== this.mgrName)
        console.log(response + "response")
        console.log(this.approvers + "approvers")
      })
  }


}