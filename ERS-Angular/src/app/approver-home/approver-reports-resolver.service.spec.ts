import { TestBed, inject } from '@angular/core/testing';

import { ApproverReportsResolverService } from './approver-reports-resolver.service';

describe('ApproverReportsResolverService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ApproverReportsResolverService]
    });
  });

  it('should be created', inject([ApproverReportsResolverService], (service: ApproverReportsResolverService) => {
    expect(service).toBeTruthy();
  }));
});
