import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TrainingDay } from '../model/TrainingDay';
import { TrainingService } from '../service/training.service';

@Component({
  selector: 'app-confirm-arrival',
  templateUrl: './confirm-arrival.component.html',
  styleUrls: ['./confirm-arrival.component.css']
})
export class ConfirmArrivalComponent implements OnInit {

  availableDevices: MediaDeviceInfo[];
  currentDevice: MediaDeviceInfo = null;
  @Input() training : TrainingDay;
  @Input() date : string;
  result : string = '';
  alreadyArrived : boolean;
  message : string = '';

  constructor(public activeModal: NgbActiveModal, private trainingService: TrainingService) { }

  ngOnInit(): void {
    this.alreadyArrived = false;
  }

  onCodeResult(resultString: string): void {
    this.result = resultString;
    if(this.result != ''){
      let body = {
        'trainingDay': this.training,
        'simpleUser': this.result.split(',')[1],
        'date' : this.date
      }

      this.trainingService.confirmArrival(body).subscribe(
        result => {

        }, error => {


          if (error.status === 400 && error.error === 'Already arrived.') {
            
            this.message = this.result.split(',')[1] + " already arrived.";
            this.alreadyArrived = true;
            setTimeout(() => this.alreadyArrived = false, 2000);
          }else if (error.status === 400 && error.error === 'No schedule training for this user.') {
            this.message = "No schedule training for " + this.result.split(',')[1];
            this.alreadyArrived = true;
            setTimeout(() => this.alreadyArrived = false, 2000);
          } else if (error.status === 200) {
            this.message = this.result.split(',')[1] + " successfully arrived.";
            this.alreadyArrived = true;
            setTimeout(() => this.alreadyArrived = false, 2000);
          }

        }
      );
    }
  }

}
