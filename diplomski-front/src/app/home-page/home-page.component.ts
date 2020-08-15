import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { TrainingService } from '../service/training.service';
import { WeekTraining } from '../model/WeekTraining';
import { TrainingDay } from '../model/TrainingDay';
import { timer } from 'rxjs';
import { NewTrainingDialogComponent } from '../new-training-dialog/new-training-dialog.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private modalService: NgbModal, private loginService: LoginService, private router: Router, public service: UserService, private trainingService : TrainingService) { }

  weekTrainings : WeekTraining[] = [];
  trainings : TrainingDay[] = [];
  selectedItem : any;
  date : string;
  now : Date = new Date(); //danasnji datum
  now1 : string;
  firstInWeek : Date;
  today : Date;
  endTime : any;
  @ViewChild(NewTrainingDialogComponent) childNewTraining: NewTrainingDialogComponent;

  ngOnInit(): void {
    
    if(!this.loginService.isTokenValid()){
      this.loginService.removeToken();
      this.router.navigateByUrl("login");
    }

    this.getAllTrainings();
  }

  getAllTrainings(){
    this.trainingService.getAllTrainings().subscribe(data=>{
      this.weekTrainings = data;
      this.trainings = this.weekTrainings[0].allTrainings;

      this.selectedItem = this.weekTrainings[0];
      this.date = this.weekTrainings[0].date;

      const source = timer(0, 2000);

      if(this.isSimpleUser()){
        source.subscribe(x => {
          this.firstInWeek = new Date(this.date);
  
          let day = this.firstInWeek.getDate();
          let month = this.firstInWeek.getMonth()+1;
          let year = this.firstInWeek.getFullYear();
          this.now1 = month + "-" + day + "-" + year;
    
          console.log(this.date);
          console.log(this.firstInWeek);
          console.log(day);
          this.today = new Date(this.now1); //prvi dan za trening
    
          console.log(this.today);
          console.log(this.now);
          if(this.datesAreOnSameDay(this.today, this.now)){
            for(let training of this.trainings){
    
              this.endTime = training.training.duration + +training.startsAt.split(":")[0];
              if (this.endTime < 10) {
                this.endTime = "0" + this.endTime;
              }
              this.endTime = this.endTime + ":00:00";
              let timeArr = this.endTime.split(":");
    
              console.log(year);
              console.log(day);
              console.log(month);
              let timeB = new Date(year, month-1, day, timeArr[0], timeArr[1], timeArr[2]);
    
              console.log("time b: " + timeB);
              if(this.now > timeB){
                this.trainings = this.trainings.filter(item => item != training);
                this.weekTrainings[0].allTrainings = this.trainings.filter(item => item != training);
              }
    
            }
          }
        });
      }
      

    });
  }

  openNewTrainingDialog(){
    const modalRef = this.modalService.open(NewTrainingDialogComponent);
  }

  datesAreOnSameDay = (first : Date, second : Date) => {
    return first.getFullYear() === second.getFullYear() && first.getMonth() === second.getMonth() && first.getDate() === second.getDate();
  }

  logout() {
    this.loginService.removeToken();
    this.router.navigateByUrl("login");
  }

  selectedDay(weekTraining : WeekTraining){
    this.trainings = weekTraining.allTrainings;
    this.selectedItem = weekTraining;
    this.date = weekTraining.date;
  }

  isActive(item) {
    return this.selectedItem === item;
  };

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


}
