import { Component } from '@angular/core';

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
  user: User = { username: "", password: "" }

  onLogin() {
    console.log(this.user)
  }
}
