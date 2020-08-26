import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserLogin } from '../model/UserLogin';
import { ContactForm } from '../model/ContactForm';
import { Observable } from 'rxjs';
import { User } from '../model/User';

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

  sendMessage(contactForm : ContactForm){
    return this.http.post('http://localhost:9000/user/send-message', contactForm);
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

  getAllUsers() : Observable<any>{
    return this.http.get<User[]>('http://localhost:9000/user/all');
  }


  unblock(username : String){
    return this.http.put('http://localhost:9000/user/unblock', username);
  }
}
