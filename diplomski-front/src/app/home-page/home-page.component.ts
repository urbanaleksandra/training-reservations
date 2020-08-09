import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { TrainingService } from '../service/training.service';
import { WeekTraining } from '../model/WeekTraining';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router, public service: UserService, private trainingService : TrainingService) { }

  weekTrainings : WeekTraining[] = [];

  ngOnInit(): void {
    this.getAllTrainings();
  }

  logout() {
    this.loginService.removeToken();
    this.router.navigateByUrl("login");
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

  getAllTrainings(){
    this.trainingService.getAllTrainings().subscribe(data=>{
      this.weekTrainings = data;
    });
  }

}
