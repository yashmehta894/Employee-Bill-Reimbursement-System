import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';

@Injectable()
export class SharedService {

  constructor() {}

  private log = new Subject<boolean>();

  changeEmitted = this.log.asObservable();

  private report = new Subject<any>();

  newReport = this.report.asObservable();

  setLoggedIn() {
    this.log.next(true)
  }

  setNewReport(newReport) {
    this.report.next(newReport)
  }


}
