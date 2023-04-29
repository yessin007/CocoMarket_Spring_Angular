import { TestBed } from '@angular/core/testing';

import { ImageProceesingsService } from './image-proceesings.service';

describe('ImageProceesingsService', () => {
  let service: ImageProceesingsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageProceesingsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
