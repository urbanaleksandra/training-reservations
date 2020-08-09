import { Injectable } from '@angular/core';
import { UserLogin } from '../model/UserLogin';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  private access_token = null;

  login(user: UserLogin) {
    return this.http.post('http://localhost:9000/auth/login', user)
      .pipe(map((res) => {
        console.log("uspeo login" + res);
        this.access_token = res["accessToken"];
      }));
  }

  setToken(token) {
    this.access_token = token;
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

  isTokenValid() {
      return this.access_token != "error";
  }

  removeToken() {
    this.access_token = null;
  }

}
