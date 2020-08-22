import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../model/User';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-users-dialog',
  templateUrl: './users-dialog.component.html',
  styleUrls: ['./users-dialog.component.css']
})
export class UsersDialogComponent implements OnInit {

  constructor(public activeModal: NgbActiveModal, private userService: UserService) { }

  users : User[] = [];

  ngOnInit(): void {

    this.getAllUsers();
  }

  unblock(username : String){
    console.log('usao');
    this.userService.unblock(username).subscribe(data=>{
      console.log(data);
      this.getAllUsers();
    },
    error=>{
      this.getAllUsers();
    });
  }

  getAllUsers(){
    this.userService.getAllUsers().subscribe(data=>{
      this.users = data;
    });
  }

}
