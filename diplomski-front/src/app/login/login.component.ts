import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from '../model/UserLogin';
import { LoginService } from '../service/login.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private service: LoginService, private  userService : UserService) { }

  username : String = "";
  password : String = "";
  user: UserLogin = new UserLogin();
  message: String = "";

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.user);
    this.service.login(this.user).subscribe(
      result => {
        if(!this.service.isTokenValid()) {
          console.log('nije validan token');
          this.message = "This user does not exist."
          this.user = new UserLogin();
          this.router.navigateByUrl("");
        } else {
          console.log('validan token');
          this.userService.initUser();
          this.router.navigateByUrl("home-page");
        }  
      },
      error => {
        console.log(error);
        this.router.navigateByUrl("login");
      }
    );
  }

}
