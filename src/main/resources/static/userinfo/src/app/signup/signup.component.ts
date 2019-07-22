import {Component, ElementRef, HostListener, OnInit} from '@angular/core';
import {SignupService} from "./signup.service";
import {Router, RouterModule} from '@angular/router';
import { LoginAuthService } from "../login/login-auth.service";
import {User} from "../user";

declare var $: any;


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user:any;
  rematch:any;
  message:any;
  phone:any;
  notMatched:boolean;
  pass:any;
  conPass:any;
  matched:boolean;
  success:boolean;
  emailMessage:any;
  userNameMessage:any;
  userNamesuccess:boolean;
  loginUser :any={};
  constructor( private signupService:SignupService,
               private router: Router,
               private loginAuthService :LoginAuthService,
               private el: ElementRef) {

    this.loginAuthService.isLoggedIn();
  }

  ngOnInit() {
    this.phone=null;
    this.notMatched = true;
    this.matched = false;
    this.success = true;
    this.user =new User();

    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    if(this.loginUser != null){
      this.router.navigate(['dashBoard']);
    }

    $('input[required],select[required]').each(function(){
      $(this).prev('label').after("<span style='color:red'>*</span>");
    });

    this.user= {};

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
    this.pass = this.user.password;
    this.conPass = this.rematch;
    if(this.user.password != null && confirmPassword != null){
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


  createAccount( signupForm: any){

    if(this.matched && this.notMatched && this.success && this.userNamesuccess == true){

      console.log(this.user);

      this.signupService.saveUser(this.user).subscribe((response)=>{
        if(response){

          signupForm.reset();
          this.router.navigate(['']);
        }

      },error => {
        console.log(error)
      })
    }
  }

  checkEmail(){
    this.success=true;

    let pattern = new RegExp("[a-zA-Z0-9.-_]{1,}@[a-zA-Z.-]{2,}[.]{1}[a-zA-Z]{2,}");

    if(this.user.email == null){
      this.success = true;
    }else{
      if (("" + pattern.test(this.user.email)) == 'false'){

        this.success = false;
        this.emailMessage = "Invalid Email Address";
      }
      else{
        this.signupService.checkEmail(this.user.email).subscribe((response)=>{
          if(response){
            this.success=response.message;
            this.emailMessage = "Email Already Used";
          }
        })
      }
    }


  }

  checkUserName() {
    this.success = null;
    this.signupService.checkUserName(this.user.username).subscribe((response) => {
      if (response.message==='false') {
        this.userNamesuccess = false;
        this.userNameMessage = "Username Already Used";
      }else {
        this.userNamesuccess=true;
      }
    })

  }

}
