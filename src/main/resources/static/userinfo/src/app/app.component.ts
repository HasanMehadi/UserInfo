import { Component } from "@angular/core";
import {Router} from "@angular/router";
import {ConstantService} from "./constant.service";
import {LoginAuthService} from "./login/login-auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'USERINFO';

  constructor( private loginAuthService: LoginAuthService ,
               private router: Router,
               private constant: ConstantService){

    this.constant.currentStatus = this.loginAuthService.getStatus().subscribe( (currentStatus)=>{
      this.constant.currentStatus = currentStatus;
    })

  }
}
