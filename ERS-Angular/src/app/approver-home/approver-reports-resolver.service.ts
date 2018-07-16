import {ApproverService} from './approver.service';
import {Injectable} from '@angular/core';
import {Resolve} from '@angular/router';
import {CustomRequestOptions} from '../custom-request-options.service';

@Injectable()
export class ApproverReportsResolverService implements Resolve<any>{

  constructor(private _approverService: ApproverService) {}

  options = new CustomRequestOptions();
  resolve() {
    return this._approverService.getReportsById(2)
  }

}
