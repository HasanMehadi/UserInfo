import {Injectable} from '@angular/core';
import {JwtHelperService} from "@auth0/angular-jwt";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ConstantService {

  mainUrl = "http://localhost:8080/";
  response: any;
  currentStatus: any;
  loginUser: any = {};
  user: any;
  jwt_decoded_token: any;
  passTrigger: any;

  constructor(private route: Router, private http: HttpClient) {

    this.passTrigger = false;
  }


  isAuthenticated() {

    var jwtHelper = new JwtHelperService();

    this.loginUser = JSON.parse(localStorage.getItem('currentUser'));
    this.jwt_decoded_token = jwtHelper.decodeToken(this.loginUser.token);

    console.log(this.jwt_decoded_token);
    if (jwtHelper.getTokenExpirationDate(this.loginUser.token).getTime() < (new Date().setSeconds(300))) {
      console.log("True");

    } else {

      this.user = this.jwt_decoded_token.sub;
      console.log(this.user);
      console.log("False");
    }

    if (jwtHelper.getTokenExpirationDate(this.loginUser.token) < (new Date)) {
      localStorage.removeItem('currentUser');
      this.route.navigate(['']);
    }

    /*if(jwtHelper.getTokenExpirationDate(token) < (new Date().getTime())){

    }*/
  }
}
