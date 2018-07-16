import {CustomRequestOptions} from './custom-request-options.service';
import {SharedService} from './shared.service';
import {Injectable} from '@angular/core';


import {Http, Response, Headers} from '@angular/http'
import {Observable} from 'rxjs';
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";


@Injectable()
export class SubmitterService {

  apiUrl = "http://localhost:8081/submitter/"
  constructor(private _http: Http,
    private _sharedService: SharedService) {}

  options = new CustomRequestOptions();

  addReport(reportForm, bills, date,empId, images) {
    let data = {
      "employeeId": empId,
      "bills": bills,
      "date": date,
      "approvedBy": reportForm.approverName,
      "comment": reportForm.comments,
      "costcenter": reportForm.costCenter,
      "images": images
    }


    //console.log(reportForm.approverName)

    let apiUrl = this.apiUrl + "report";
    //    let addreportBody=JSON.stringify({
    //      employeeId:employeeId,
    //      bills:bills,
    //      date:date,
    //      approvedBy:approvedBy,
    //      cont:comment
    //    })
    let myHeader = new Headers();
    myHeader.append('content-type', 'application/json')

    let opt = new CustomRequestOptions();
    opt.headers = myHeader;
    console.log(data)
    console.log(JSON.stringify(data))
    this._sharedService.setNewReport(data)
    return this._http.post(apiUrl, data, {headers: opt.headers})
    //.map(resp => resp.json())
  }



  reSubmit(bills, status, reportId) {
    let data = {

      "bills": bills,
      "reportId": reportId,
      "status": status
    }
    let apiUrl = this.apiUrl + "report";
    console.log(data);
    let myHeader = new Headers();
    myHeader.append('content-type', 'application/json')


    let opt = new CustomRequestOptions();
    opt.headers = myHeader;
    console.log(JSON.stringify(data))
    return this._http.put(apiUrl, data, {headers: opt.headers})
    //.map(resp => resp.json())

  }





  getBills(reportId) {
    let apiUrl = this.apiUrl + "bills?reportId=" + reportId;
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);
  }

  getCostCenters() {
    //let headers=new Headers();
    //headers.append('content-type','application/json')
    let apiUrl = this.apiUrl + "costCenter"
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);

  }

  getApproverByLevel(costcenterId, level) {
    let apiUrl = this.apiUrl + "approver?costcenterId=" + costcenterId + "&level=" + level;
    console.log(apiUrl)
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);
  }


  getReportByEmployeeId(empId) {
    let apiUrl = this.apiUrl + "report?empId=" + empId;
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);
  }

  getRemainingAmt(empId, date) {
    let apiUrl = this.apiUrl + "getAmt?empId=" + empId + "&date=" + date;
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);
  }

  getTotalAmount(empId, date) {
    let apiUrl = this.apiUrl + "getTotalAmt?empId=" + empId + "&date=" + date;
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);
  }

  getMgr(mgrId) {
    let apiUrl = this.apiUrl + "getMgrName?mgrId=" + mgrId;
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);
  }

  getCostCenterById(empId) {
    let apiUrl = this.apiUrl + "getCostCenterById?empId=" + empId;
    return this._http.get(apiUrl, {headers: this.options.headers})
      .map(resp => resp.json())
      .catch(this.handleError);
  }



  handleError(error: Response) {
    return Observable.throw(error.json().error || 'Server error');
  }

}