import { Injectable } from '@angular/core';
import { UserLogin } from '../model/UserLogin';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }
  private readonly baseURI = environment.baseURI;

  public access_token = null;

  login(user: UserLogin) {
    return this.http.post(this.baseURI + 'auth/login', user)
      .pipe(map((res) => {
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
    if(this.access_token == null || this.access_token == 'error'){
      return false;
    }else{
      return true;
    }
  }

  removeToken() {
    this.access_token = null;
  }

}
