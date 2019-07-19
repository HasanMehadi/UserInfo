import {Component, ElementRef, HostListener, OnInit} from '@angular/core';
import {ForgetPassword} from "../forget-password";
import {LoginAuthService} from "../login/login-auth.service";
import {Router} from "@angular/router";

import {UserPanelService} from "./user-panel.service";

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {


  forgetPassword: any;

  rematch:any;

  notMatched:boolean;
  pass:any;
  conPass:any;
  matched:any;

  loginUser :any={};

  constructor( private userPanelService:UserPanelService,
               private router: Router,
               private loginAuthService :LoginAuthService,
               private el: ElementRef) { }

  ngOnInit() {
    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));

    this.forgetPassword = new ForgetPassword();
    this.notMatched= true;

  }

  changePassword(){
    this.forgetPassword.username = this.loginUser.username;
    this.forgetPassword.token = this.loginUser.token;
    this.userPanelService.changePasswordToNew(this.loginUser.token, this.forgetPassword).subscribe((respone)=>{
      console.log(respone);
    });
  }

  @HostListener('submit', ['$event'])
  onFormSubmit() {
    const invalidElements = this.el.nativeElement.querySelectorAll('.ng-invalid');
    if (invalidElements.length > 0) {
      invalidElements[1].focus();
    } else {
//console();
    }
  }

  matchPass(confirmPassword) {

    this.pass = this.forgetPassword.newPassword;
    this.conPass = this.rematch;


    if(this.forgetPassword.newPassword != null && this.pass != null){
      if (!confirmPassword) {
        this.notMatched = true;
      } else {
        if (this.pass != this.conPass) {
          this.notMatched = false;
          this.matched= false;
        } else {
          this.notMatched = true;
          this.matched=true;
        }
      }
    }
  };
}
