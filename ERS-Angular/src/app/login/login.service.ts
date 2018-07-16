import {Injectable} from '@angular/core';
import {Http, Response, URLSearchParams, Headers} from '@angular/http';
import {RequestOptions} from '@angular/http'
//import {CustomRequestOptions} from '../CustomRequestOptions'
import {CustomRequestOptions} from '../custom-request-options.service';
import {Route, Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable()
export class LoginService {

  url = "http://localhost:8081/login"
  token: string;
  reports: any;
  constructor(private _http: Http, private _route: Router
  ) {}
  ngOnInit() {

  }

  response: Response;
  login(employeeId, password) {
    let myHeaders = new Headers();
    myHeaders.append("id", employeeId)
    myHeaders.append("password", password)
    myHeaders.append("auth", 'true');
    let options = new CustomRequestOptions();
    options.headers = myHeaders;
    //console.log(options);
    console.log(options.headers);
    return this._http.get(this.url, {headers: options.headers})
      .map(res => res.json())
      .do(data => {console.log('Employee data is' + JSON.stringify(data))})
      .subscribe(response => {
        this.response = response, this.token = response[0], this.storeToken()

        if (response[1].role == "payroll") {
          this._route.navigate(["/payroll/" + response[1].empId])
        }
        else if (response[1].role == "approver") {
          this._route.navigate(["/approver/" + response[1].empId])
        } else if (response[1].role == "submitter") {
          this._route.navigate(["/submitter/" + response[1].empId])
        }

      })
  }



  storeToken() {
    localStorage.clear();
    localStorage.setItem('token', this.token);
    localStorage.setItem('empId', this.response[1].empId);
    localStorage.setItem('level', this.response[1].level);
    localStorage.setItem('name', this.response[1].name);
    localStorage.setItem('mgrId', this.response[1].mgrId);
    localStorage.setItem('role', this.response[1].role)
    localStorage.setItem('costCenterName', this.response[1].costcenter.centerName)
  }

  setReports(reports) {
    this.reports = reports
  }

  purgeStorage() {
    localStorage.clear();
  }

  removeToken() {
    let headers = new Headers();
    let token = localStorage.getItem("token")
    headers.append("token", token)
    this._http.get("http://localhost:8081/logout", {headers: headers}).subscribe()
    this._route.navigateByUrl("/login")
  }

  isLoggedIn() {
    if (localStorage.getItem("token") != null) {
      return true;
    }
    return false;
  }
  isPayroll() {
    if (localStorage.getItem("role") == "payroll") {
      return true;
    }
    return false;
  }

  isApprover() {
    if (localStorage.getItem("role") == "approver") {
      return true;
    }
    return false;

  }

}
