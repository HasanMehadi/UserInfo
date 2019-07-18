import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {NgxPaginationModule} from "ngx-pagination";
import {SignupComponent} from "../signup.component";
import {SignupService} from "../signup.service";

@NgModule({
  declarations: [SignupComponent],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    NgxPaginationModule
  ],
  providers:[SignupService]
})
export class SignupModule { }
