import { TestBed } from '@angular/core/testing';

import { StrCtlgResolverService } from './str-ctlg-resolver.service';

describe('StrCtlgResolverService', () => {
  let service: StrCtlgResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StrCtlgResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
