import { TestBed } from '@angular/core/testing';

import { ProviderResolverService } from './provider-resolver.service';

describe('ProviderResolverService', () => {
  let service: ProviderResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProviderResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
