import { TestBed } from '@angular/core/testing';

import { StoreResolverService } from './store-resolver.service';

describe('StoreResolverService', () => {
  let service: StoreResolverService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StoreResolverService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
