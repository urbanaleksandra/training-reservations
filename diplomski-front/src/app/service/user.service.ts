import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserLogin } from '../model/UserLogin';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  currentUser = null;
  roles = null;

  constructor(private http: HttpClient) {
  }

  initUser() {
    return this.http.get('http://localhost:9000/user').subscribe(
      res => {
        if (res !== null) {
          console.log(res);
          this.currentUser = res;
          this.roles = res["roles"];
        }
      },
      error => {
        console.log(error);
      });
  }

  getUser() {
    return this.currentUser;
  }

  userIsPresent() {
    return this.currentUser != undefined && this.currentUser != null;
  }

  addUser(user : UserLogin) {
    return this.http.post("http://localhost:9000/auth/signup", user);
  }

  confirmAccount(token:String){
    return this.http.get<UserLogin>('http://localhost:9000/auth/confirmAccount/'+token)
  }

  getRoles() {
    return this.roles;
  }

  getRoleName() {
    if(this.roles != null) {
      if(this.roles[0].name == "ROLE_ADMIN")
        return "admin";
      else if (this.roles[0].name == "ROLE_SIMPLE_USER")
        return "user";
      else
        return "employee";
    }
  }
}
