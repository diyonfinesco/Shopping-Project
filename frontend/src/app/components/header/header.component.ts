import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: []
})
export class HeaderComponent {

  constructor(private userService: UserService, private router: Router) { }

  getAuthenticatedUser() {
    return this.userService.getAuthenticatedUser()
  }

  isAuthenticated() {
    return this.userService.isAuthenticated()
  }

  isAdmin() {
    return this.userService.isAdmin()
  }

  onLogout() {
    this.userService.logout()
    this.router.navigateByUrl('/login')
  }
}
