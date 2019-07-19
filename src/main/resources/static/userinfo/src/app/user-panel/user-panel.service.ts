import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ConstantService} from "../constant.service";

@Injectable({
  providedIn: 'root'
})
export class UserPanelService {



  constructor(private router: Router, private constantService: ConstantService, private http: HttpClient) {

  }

  changePasswordToNew(token: any,forgetPassword: any){


    const headers = new HttpHeaders({'Authorization': 'Bearer '+token});
    return this.http.post(this.constantService.mainUrl+'user/updatePassword',forgetPassword,{headers: headers})

  }
}
