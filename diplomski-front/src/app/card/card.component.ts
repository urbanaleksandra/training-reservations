import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { TrainingDay } from '../model/TrainingDay';
import { UserService } from '../service/user.service';
import { TrainingService } from '../service/training.service';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmArrivalComponent } from '../confirm-arrival/confirm-arrival.component';
import { NewTrainingDialogComponent } from '../new-training-dialog/new-training-dialog.component';
import { DayAndTime } from '../model/DayAndTime';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(private modalService: NgbModal, private router: Router, private userService: UserService, private trainingService: TrainingService, private loginService: LoginService, public service: UserService) { }

  @Input() training: TrainingDay;
  @Input() date: string;
  endTime: any;
  alreadySchedule: boolean;
  message: string;
  numberOfScheduled: number;
  left: number;
  @Output() passNewTrainingDays: EventEmitter<any> = new EventEmitter();

  ngOnInit(): void {

    if (this.userService.currentUser == null) {
      this.router.navigateByUrl("");
    }


    this.endTime = this.training.training.duration + +this.training.startsAt.split(":")[0];
    if (this.endTime < 10) {
      this.endTime = "0" + this.endTime;
    } else if (this.endTime >= 24) {
      this.endTime = this.endTime - 24;
      if (this.endTime < 10) {
        this.endTime = "0" + this.endTime;
      }
    }


    this.endTime = this.endTime + ":" + this.training.startsAt.split(":")[1];

    this.alreadySchedule = false;

    this.getNumberOfScheduled(this.training, this.date);
  }

  getNumberOfScheduled(training: TrainingDay, date: string) {
    this.trainingService.getNumberOfScheduled(training.id, date).subscribe(data => {
      this.numberOfScheduled = data;
      this.left = this.training.training.capacity - this.numberOfScheduled;
    });
  }

  isFull(): boolean {
    if (this.left <= 0) {
      return true;
    } else {
      return false;
    }
  }

  isAdmin() {
    if (this.service.roles != null) {
      for (let role of this.service.roles) {
        if (role.name == "ROLE_ADMIN")
          return true;
      }
      return false;
    }
  }

  isSimpleUser() {
    if (this.service.roles != null) {
      for (let role of this.service.roles) {
        if (role.name == "ROLE_SIMPLE_USER")
          return true;
      }
      return false;
    }
  }

  isEmployee() {
    if (this.service.roles != null) {
      for (let role of this.service.roles) {
        if (role.name == "ROLE_EMPLOYEE")
          return true;
      }
      return false;
    }
  }

  confirmArrival() {
    const modalRef = this.modalService.open(ConfirmArrivalComponent);
    modalRef.componentInstance.training = this.training;
    modalRef.componentInstance.date = this.date;
  }

  schedule() {

    if (!this.isFull()) {
      let body = {
        'trainingDay': this.training,
        'simpleUser': this.userService.currentUser.username,
        'date': this.date
      }

      this.trainingService.scheduleTraining(body).subscribe(
        result => {
          this.getNumberOfScheduled(this.training, this.date);

        }, error => {

          if (error.status === 400) {
            this.message = "You already schedule it.";
            this.alreadySchedule = true;
            setTimeout(() => this.alreadySchedule = false, 2000);
          } else if (error.status === 200) {
            this.message = "Successfully scheduled.";
            this.alreadySchedule = true;
            setTimeout(() => this.alreadySchedule = false, 2000);
          } else if (error.status === 406) {
            this.message = "Training capacity full. You are late.";
            this.alreadySchedule = true;
            setTimeout(() => this.alreadySchedule = false, 2000);
          }
          this.getNumberOfScheduled(this.training, this.date);

        }
      );

    } else {
      this.message = "Training capacity full.";
      this.alreadySchedule = true;
      setTimeout(() => this.alreadySchedule = false, 2000);
    }
  }

  async updateTraining(): Promise<any> {
    let trainingDays: TrainingDay[];
    await this.trainingService.getAllByTraining(this.training.training.id).subscribe(data => {
      trainingDays = data;
      const modalRef = this.modalService.open(NewTrainingDialogComponent);
      modalRef.componentInstance.mode = 'update';
      modalRef.componentInstance.trainingToUpdate = this.training.training;
      modalRef.componentInstance.trainingID = this.training.id;
      modalRef.componentInstance.trainingDays = trainingDays;
      modalRef.componentInstance.trainerNameInput = this.training.trainer;
      modalRef.componentInstance.selectedTrainingDay = this.training;
      modalRef.componentInstance.updatedTraining.subscribe(updated => {
        this.passNewTrainingDays.emit();
      });
    });

  }

}
