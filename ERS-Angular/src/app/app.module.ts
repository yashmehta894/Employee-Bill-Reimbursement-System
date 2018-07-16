import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {ApproverHomeComponent} from './approver-home/approver-home.component';
import {ApproverReportsResolverService} from './approver-home/approver-reports-resolver.service';
import {ApproverService} from './approver-home/approver.service';
import {BillsComponent} from './bills/bills.component';
import {CustomRequestOptions} from './custom-request-options.service';
import {EmployeeDetailsComponent} from './employee-details/employee-details.component';
import {HttpModule, RequestOptions} from '@angular/http';
import {PayrollComponent} from './payroll/payroll.component';
import {PayrollService} from './payroll/payroll.service';
import {ReportListComponent} from './report-list/report-list.component';
import {ReportComponent} from './report/report.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {LoginService} from './login/login.service';
import {SharedService} from './shared.service';
import {SubmitterHomeComponent} from './submitter-home/submitter-home.component';
import {SubmitterReportListComponent} from './submitter-report-list/submitter-report-list.component';
import {SubmitterService} from './submitter.service';
import {SubmittersFormComponent} from './submitters-form/submitters-form.component';
import {ApproverGuard} from './guards/ApproverGuard';
import {CanActivateViaAuthGuard} from './guards/CanActivateAuthGuard';
import {PayRollGuard} from './guards/PayRollGuard';
import {HistoryReportsComponent} from './history-reports/history-reports.component';
import {SubmitterBillsComponent} from './submitter-bills/submitter-bills.component';


import {ImageUploadModule} from 'ng2-imageupload'
let routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "approver/:id", component: ApproverHomeComponent, canActivate: [CanActivateViaAuthGuard, ApproverGuard]},//, resolve: {reports:ApproverReportsResolverService}},
  {path: "payroll/:id", component: PayrollComponent, canActivate: [CanActivateViaAuthGuard, PayRollGuard]},
  {path: "login", component: LoginComponent},
  {path: "submitter/:id", component: SubmitterHomeComponent, canActivate: [CanActivateViaAuthGuard]},
  {path: "add", component: SubmittersFormComponent, canActivate: [CanActivateViaAuthGuard]},
  {path: "history", component: HistoryReportsComponent, canActivate: [CanActivateViaAuthGuard]}
]

@NgModule({
  declarations: [
    AppComponent,
    ApproverHomeComponent,
    PayrollComponent,
    ReportListComponent,
    ReportComponent,
    BillsComponent,
    LoginComponent,
    SubmitterReportListComponent,
    SubmitterHomeComponent,
    SubmittersFormComponent,
    EmployeeDetailsComponent,
    SubmitterBillsComponent,
    HistoryReportsComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    ImageUploadModule,
    RouterModule.forRoot(routes)
  ],
  providers: [ApproverService,
    PayrollService,
    LoginService,
    ApproverReportsResolverService,
    SharedService,
    SubmitterService,
    ApproverGuard,
    PayRollGuard,
    CanActivateViaAuthGuard,
    {provide: RequestOptions, useClass: CustomRequestOptions}],
  bootstrap: [AppComponent]
})
export class AppModule {}
