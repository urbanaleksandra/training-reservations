import { Component, OnInit } from '@angular/core';
import { UserLogin } from '../model/UserLogin';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private service: UserService,
    private router: Router) { }

  user: UserLogin = new UserLogin();

  message: String = "";

  confirmPass:boolean;

  ngOnInit(): void {
    this.confirmPass = true;
  }

  onSubmit() {

    if(this.user.password === this.user.confirmPassword){
      this.confirmPass = true;
      this.service.addUser(this.user).subscribe(
        result => {
          console.log("successful registration");
          this.router.navigateByUrl('login');
        }, error => {
          console.log(error);
          this.message = "Username already exists.";
        }
      );
    }else{
      this.confirmPass = false;
    }
  }

}
