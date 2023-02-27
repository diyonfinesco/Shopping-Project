import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../shared/user/user.service';
import { Router } from '@angular/router';
import { User } from '../shared/user/user';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: []
})
export class RegisterComponent {

  constructor(private restApi: UserService, private router: Router, private fb: FormBuilder) { }

  userForm = this.fb.group({
    name: ["", Validators.required],
    username: ["", Validators.email],
    password: ["", Validators.required],
  })

  async onRegister() {
    this.restApi.createUser(this.userForm.value as User).subscribe((data) => this.router.navigate(['/']))
  }

}
