import { TestBed } from '@angular/core/testing';

import { UserPanelService } from './user-panel.service';

describe('UserPanelService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserPanelService = TestBed.get(UserPanelService);
    expect(service).toBeTruthy();
  });
});
