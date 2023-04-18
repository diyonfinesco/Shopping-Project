import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-t-header',
  templateUrl: './t-header.component.html',
  styleUrls: []
})
export class THeaderComponent {
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
