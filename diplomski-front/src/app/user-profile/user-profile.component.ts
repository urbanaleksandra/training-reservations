import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserService, private loginService: LoginService, private router: Router) { }

  user : any;
  value : string = '';

  ngOnInit(): void {
    
    if(!this.loginService.isTokenValid()){
      this.loginService.removeToken();
      this.router.navigateByUrl("login");
    }else{
      this.user = this.userService.currentUser;
    
      this.value = this.user.id + ',' + this.user.username;
    }
    
  }

  logout() {
    this.loginService.removeToken();
    this.router.navigateByUrl("login");
  }

  

}
