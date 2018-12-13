import { TestBed } from '@angular/core/testing';

import { VideogamesService } from './videogames.service';

describe('VideogamesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VideogamesService = TestBed.get(VideogamesService);
    expect(service).toBeTruthy();
  });
});
