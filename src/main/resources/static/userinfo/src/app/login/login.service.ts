import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ConstantService} from "../constant.service";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  headers =new HttpHeaders({'Access-Control-Allow-Origin':'*'});

  constructor(private http: HttpClient, private constantService:ConstantService) {
    this.http = http;
  }

  public loginUser(user:any): Observable<any>{
    return this.http.post(this.constantService.mainUrl+"login",user,{headers: this.headers});

  }
}
