import { Component, OnInit, Input } from '@angular/core';
import { TrainingDay } from '../model/TrainingDay';
import { UserService } from '../service/user.service';
import { TrainingService } from '../service/training.service';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor(private router: Router, private userService: UserService, private trainingService: TrainingService, private loginService : LoginService, public service: UserService) { }

  @Input() training: TrainingDay;
  @Input() date : string;
  endTime: any;
  alreadySchedule: boolean;
  message: string;
  numberOfScheduled: number;
  left: number;

  ngOnInit(): void {

    if(this.userService.currentUser == null){
      this.router.navigateByUrl("");
    }

    console.log(this.date);

    this.endTime = this.training.training.duration + +this.training.startsAt.split(":")[0];
    if (this.endTime < 10) {
      this.endTime = "0" + this.endTime;
    }
    this.endTime = this.endTime + ":00";
    console.log(this.endTime);

    this.alreadySchedule = false;

    this.getNumberOfScheduled(this.training);
  }

  getNumberOfScheduled(training: TrainingDay) {
    this.trainingService.getNumberOfScheduled(training.id).subscribe(data => {
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

  schedule() {

    if (!this.isFull()) {
      let body = {
        'trainingDay': this.training,
        'simpleUser': this.userService.currentUser.username
      }

      this.trainingService.scheduleTraining(body).subscribe(
        result => {
          console.log('prosao schedule' + result);
          this.getNumberOfScheduled(this.training);

        }, error => {

          if (error.status === 400) {
            console.log(error);
            this.message = "You already schedule it.";
            this.alreadySchedule = true;
            setTimeout(() => this.alreadySchedule = false, 2000);
          } else if (error.status === 200) {
            this.message = "Successfully scheduled.";
            this.alreadySchedule = true;
            setTimeout(() => this.alreadySchedule = false, 2000);
          }
          this.getNumberOfScheduled(this.training);

        }
      );
    } else {
      this.message = "Training capacity full.";
      this.alreadySchedule = true;
      setTimeout(() => this.alreadySchedule = false, 2000);
    }
  }

}
