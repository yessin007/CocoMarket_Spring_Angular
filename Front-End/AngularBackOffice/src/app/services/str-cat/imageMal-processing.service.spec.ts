import { TestBed } from '@angular/core/testing';

import { ImageMalProcessingService } from './imageMal-processing.service';

describe('ImageProcessingService', () => {
  let service: ImageMalProcessingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ImageMalProcessingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
