import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantService {

  mainUrl = "http://localhost:8080/";
  response:any;
  currentStatus: any;

  passTrigger:any;
  constructor() {

    this.passTrigger = false;
  }


}
