import {LoginService} from './login/login.service';
import {SharedService} from './shared.service';
import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  log: boolean = false;

  id: number;
  constructor(private _sharedService: SharedService, private _loginService: LoginService) {}

  ngOnInit() {
    this._sharedService.changeEmitted.subscribe((login) => {this.log = login})
    if (localStorage.getItem("token") != null) {
      this.log = true;
    }
  }

  logout() {
    this._loginService.removeToken()
    this._loginService.purgeStorage();
    if (localStorage.getItem("token") == null) {
      this.log = false;
    }
    this.id = null
  }

  approverUrl() {
    this.id = +localStorage.getItem("empId");
    return "/approver/" + localStorage.getItem("empId");
  }

  payrollUrl() {
    this.id = +localStorage.getItem("empId");
    return "/payroll/" + localStorage.getItem("empId");
  }

  homeUrl() {
    let empId = localStorage.getItem("empId")
    let role = localStorage.getItem("role")

    if (empId == null)
      return "";
    if (role == "submitter") {
      return "/submitter/" + empId
    }
    if (role = "approver") {
      return "/approver/" + empId
    }
    if (role = "payroll") {
      return "/payroll/" + empId
    }

    return ""
  }
}
