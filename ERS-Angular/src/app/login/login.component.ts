import {SharedService} from '../shared.service';
import {LoginService} from './login.service';
import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {Route, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  authToken: string = 'vmp';

  constructor(private fb: FormBuilder,
    private _loginService: LoginService,
    private _route: Router,
    private _sharedService: SharedService) {};

  ngOnInit() {
    this.loginForm = this.fb.group({
      employeeId: ['', Validators.required],
      password: ['', Validators.required]
    })


  }
  login() {
    let response = this._loginService.login(this.loginForm.get('employeeId').value, this.loginForm.get("password").value)
    if (response != null) {
      console.log("inside login")
      this._sharedService.setLoggedIn()
      //      this._route.navigate(["/approver"])
    }

  }


  storeInStorage() {
    localStorage.setItem("employeeId", this.loginForm.get('employeeId').value);
    localStorage.setItem("password", this.loginForm.get('password').value);

    console.log(localStorage.getItem("password"));

  }


}
