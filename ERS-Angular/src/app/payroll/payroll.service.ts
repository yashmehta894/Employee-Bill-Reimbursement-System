import {CustomRequestOptions} from '../custom-request-options.service';
import {Injectable} from '@angular/core';
import {Http, Response, RequestOptions, Headers} from '@angular/http';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class PayrollService {

  url = "http://localhost:8081/payroll"

  options = new CustomRequestOptions();

  constructor(private _http: Http) {}

  getAllReports(status): Observable<any> {
    return this._http.get(this.url + "/report?status=" + status, {headers: this.options.headers})
      .map(response => response.json())
      .do(data => console.log('All Reports of payroll: ' + JSON.stringify(data)))
  }

  updateStatus(report) {
    let myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');
    let opt = new CustomRequestOptions();

    opt.headers = myHeaders;
    console.log("In update " + report)
    //console.log(this.url + "/report")
    //console.log(report)
    return this._http.put(this.url + "/report", JSON.stringify(report), {headers: opt.headers})
      .map(response => response.json())
      .subscribe(report => {console.log(JSON.stringify(report))})
  }

  getPaidReports() {
    return this._http.get("http://localhost:8081/payroll/history")
  }
}
