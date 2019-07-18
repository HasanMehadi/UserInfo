import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {NgxPaginationModule} from "ngx-pagination";
import {RouterModule} from "@angular/router";

import {DashBoardService} from "../dash-board.service";
import {DashBoardComponent} from "../dash-board.component";
import {AuthGuard} from "../../auth.guard";

import {UserPanelService} from "../../user-panel/user-panel.service";
import {UserPanelComponent} from "../../user-panel/user-panel.component";


@NgModule({
  declarations: [DashBoardComponent,UserPanelComponent],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    NgxPaginationModule,
    RouterModule,

  ],
  providers:[AuthGuard,DashBoardService,UserPanelService]
})
export class DashBoardModule { }
