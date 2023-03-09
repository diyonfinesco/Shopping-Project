import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: []
})
export class LoginComponent implements OnInit {
  errorMessage = ""

  constructor(private userService: UserService, private fb: FormBuilder, private router: Router) { }

  loginForm = this.fb.group({
    username: ["", [Validators.email, Validators.required]],
    password: ["", Validators.required],
  })

  ngOnInit(): void {
    if (this.isAuthenticates()) {
      this.router.navigateByUrl('/')
    }
  }

  isAuthenticates() {
    return this.userService.isAuthenticated()
  }

  async onSubmit() {
    this.errorMessage = ""
    const { username, password } = this.loginForm.value

    if (username && password) {
      this.userService.loginUser(username, password).subscribe({
        next: (data) => {
          this.userService.saveUserAndToken(data)
          this.router.navigateByUrl('/')
        },
        error: (error) => {
          this.errorMessage = error.error.message
        }
      })
    }
  }
}
