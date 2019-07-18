import { Injectable } from '@angular/core';
import {ConstantService} from "../constant.service";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class DashBoardService {

  constructor(private router: Router, private constantService: ConstantService, private http: HttpClient) {

  }

  changePassword(token: any,username: any,forgetPassword: any){
    const headers = new HttpHeaders({'Authorization': 'Bearer '+token});
    return this.http.post(this.constantService.mainUrl+'user/updatePassword',{headers: headers, params:{ username: username,forgetPassword: forgetPassword}})
  }

  getUserPage(token:any,page:any,size:any):Observable<any>{

    const headers = new HttpHeaders({'Authorization': 'Bearer '+token});
    return this.http.get(this.constantService.mainUrl+'user/users',{headers: headers, params: {page: page.toString(),size:size.toString()}});

  }

}
