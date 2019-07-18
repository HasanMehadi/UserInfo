import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SignupComponent} from "./signup/signup.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./auth.guard";
import {DashBoardComponent} from "./dash-board/dash-board.component";
import {UserPanelComponent} from "./user-panel/user-panel.component";


const routes: Routes = [

  {
    path: '',
    component: LoginComponent,
    data: {
      title: 'Log In'
    }
  },{
    path: 'signUp',
    component: SignupComponent,
    data: {
      title: 'Sign Up'
    }
  },

  {
    path: 'dashBoard',
    component: DashBoardComponent, canActivate:[AuthGuard],
    data: {
      title: 'Admin Dash Board'
    },
    children:[
      {
        path: 'changePassword',
        component:UserPanelComponent, canActivate:[AuthGuard],
        data:{
          title:'Change Password'
        }
      },
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
