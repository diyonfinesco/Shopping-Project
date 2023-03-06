import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/user/user.service';


interface User {
  username: string,
  password: string
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: []
})
export class LoginComponent {

  constructor(private restApi: UserService, private router: Router, private fb: FormBuilder) { }

  userForm = this.fb.group({
    username: ["", Validators.email],
    password: ["", Validators.required],
  })

  async onLogin() {
    const { username, password } = this.userForm.value

    if (username && password)
      this.restApi.loginUser(username, password).subscribe((data) => {
        const token = {
          tokenUser: data.username,
          tokenPass: data.password
        }
        localStorage.setItem("token", JSON.stringify(token))
        this.router.navigate(['/cart'])
      })
  }
}
