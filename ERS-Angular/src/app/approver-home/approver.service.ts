import {CustomRequestOptions} from '../custom-request-options.service';
import {Injectable} from '@angular/core';
import {Http, Response, RequestOptions, RequestMethod, Headers, Request} from '@angular/http';
import {Router, ActivatedRoute} from '@angular/router';
import {stringify} from 'querystring';
import {Observable} from 'rxjs';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class ApproverService {

  url = "http://localhost:8081/approver"
  id: any;
  data: any;
  constructor(private _http: Http,
    private _route: ActivatedRoute) {}

  options = new CustomRequestOptions();

  getReportsById(id): Observable<any> {

    let callUrl = this.url + "/report/" + id

    return this._http.get(callUrl, {headers: this.options.headers})
      .map(response => response.json())
      .do(data => console.log('All Report  By ID: ' + JSON.stringify(data)))

  }

  getReportsByLevelAndCenter(level: number, center: number): Observable<any> {
    return this._http.get(this.url + "/report?level=" + level + "&centerId=" + center, {headers: this.options.headers})
      .map(response => response.json())
      .do(data => console.log('All Report  By level and center: ' + JSON.stringify(data)))
  }

  getBills(reportId: number): Observable<any> {

    return this._http.get(this.url + "/bills?reportId=" + reportId, {headers: this.options.headers})
      .map(response => response.json())
      .do(data => console.log('All Bills of a report ' + JSON.stringify(data)))

  }

  updateStatus(report) {

    let myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    let opt = new CustomRequestOptions();
    opt.headers = myHeaders;

    return this._http.put(this.url + "/report", JSON.stringify(report), {headers: opt.headers})
      .map(response => response.json())
      .do(data => {this.data = data})
      .subscribe(value => {console.log(this.data)})
  }

  getApprovedReports() {
    return this._http.get("http://localhost:8081/approver/history?id=" + localStorage.getItem("empId"))
  }
}
