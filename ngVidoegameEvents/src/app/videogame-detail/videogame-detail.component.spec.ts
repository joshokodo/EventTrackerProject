import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VideogameDetailComponent } from './videogame-detail.component';

describe('VideogameDetailComponent', () => {
  let component: VideogameDetailComponent;
  let fixture: ComponentFixture<VideogameDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VideogameDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VideogameDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
