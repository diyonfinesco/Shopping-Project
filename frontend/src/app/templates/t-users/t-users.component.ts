import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-t-users',
  templateUrl: './t-users.component.html',
  styleUrls: []
})
export class TUsersComponent {
  users!: User[];

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    if (!this.userService.isAdmin()) {
      this.router.navigateByUrl('/')
    }
    this.userService.getUsers().subscribe((data) => this.users = data)
  }
}
