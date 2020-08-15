import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-new-training-dialog',
  templateUrl: './new-training-dialog.component.html',
  styleUrls: ['./new-training-dialog.component.css']
})
export class NewTrainingDialogComponent implements OnInit {

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
