import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {NgxPaginationModule} from "ngx-pagination";
import {LoginComponent} from "../login.component";
import {LoginService} from "../login.service";
import {LoginAuthService} from "../login-auth.service";

@NgModule({
  declarations: [LoginComponent],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    NgxPaginationModule
  ],
  providers:[LoginService, LoginAuthService]
})
export class LoginModule { }
