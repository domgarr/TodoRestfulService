import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private username : String;
  @Input() renderMenu: boolean;

  constructor(private router : Router, private loginService : LoginService) {
    this.renderMenu = false;
  }

  ngOnInit() {
    let tokenCheck = localStorage.getItem("id_token");
    if(tokenCheck != null){
    this.renderMenu = true;
    this.loginService.getUsername().subscribe((obj)=> this.username = obj.username );
    }
  }

  logout(){
    localStorage.removeItem("id_token");
    this.router.navigateByUrl('/login');
  }

  onTitleClick(){
    this.router.navigateByUrl('/login');
  }

}
