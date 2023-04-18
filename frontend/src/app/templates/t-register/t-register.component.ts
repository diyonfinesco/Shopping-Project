import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-t-register',
  templateUrl: './t-register.component.html',
  styleUrls: []
})
export class TRegisterComponent {
  errorMessage = ""
  successMessage = ""
  constructor(private userService: UserService, private router: Router, private fb: FormBuilder) { }

  registerForm = this.fb.group({
    name: ["", Validators.required],
    username: ["", Validators.email],
    password: ["", Validators.required],
  })

  async onRegister() {
    this.errorMessage = ""
    this.successMessage = ""
    const { name, username, password } = this.registerForm.value

    if (!name || !username || !password) return;
    this.userService.createUser(name, username, password).subscribe({
      next: (data) => {
        this.successMessage = "registered success. please login"
      },
      error: (error) => {
        this.errorMessage = error.error.message
      }
    })
  }
}
