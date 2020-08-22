import { Component, OnInit } from '@angular/core';
import { LoginService } from '../service/login.service';
import { Router } from '@angular/router';
import { ContactForm } from '../model/ContactForm';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router, private userService : UserService) { }

  contactForm : ContactForm = new ContactForm();
  messageValid: boolean;
  message: string = '';
  user : any;

  ngOnInit(): void {
    this.messageValid = true;
    this.user = this.userService.currentUser;
    this.contactForm.email = this.user.email;
  }

  logout() {
    this.loginService.removeToken();
    this.router.navigateByUrl("login");
  }

  onSubmit(){
    console.log(this.contactForm);

    if(this.contactForm.message === ''){
      this.messageValid = false;
    }else{
      this.messageValid = true;
    }

    if(this.messageValid === true){
      this.userService.sendMessage(this.contactForm).subscribe(result=>{
        console.log(result);
      },
      error=>{
        if(error.status == 200){
          console.log(error);
          this.message = 'Message sent.'
          this.contactForm.message = '';
          this.contactForm.subject = '';
          this.contactForm.name = '';
          setTimeout(() => this.message = '', 3000);
        }
      });
    }

  }

}
