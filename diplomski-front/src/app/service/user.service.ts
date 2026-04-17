import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserLogin } from '../model/UserLogin';
import { ContactForm } from '../model/ContactForm';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly baseURI = environment.baseURI;

  currentUser = null;
  roles = null;

  constructor(private http: HttpClient) {
  }

  initUser() {
    return this.http.get(this.baseURI + 'user').subscribe(
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
    return this.http.post(this.baseURI + "auth/signup", user);
  }

  confirmAccount(token:String){
    return this.http.get<UserLogin>(this.baseURI + 'auth/confirmAccount/' + token)
  }

  getRoles() {
    return this.roles;
  }

  sendMessage(contactForm : ContactForm){
    return this.http.post(this.baseURI + 'user/send-message', contactForm);
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
    return this.http.get<User[]>(this.baseURI + 'user/all');
  }


  unblock(username : String){
    return this.http.put(this.baseURI + 'user/unblock', username);
  }
}
