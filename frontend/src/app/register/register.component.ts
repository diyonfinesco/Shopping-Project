import { Component } from '@angular/core';
import axios from 'axios'

interface User {
  name: string,
  username: string,
  password: string
}

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: []
})
export class RegisterComponent {
  user: User = { name: "", username: "", password: "" }
  responseMessage = ""

  async onRegister() {
    try {
      this.responseMessage = ""
      const response = await axios.post("http://localhost:8080/users/register", this.user)
      console.log(response.data)
      this.responseMessage = "registered successfully!"

    } catch (error) {
      this.responseMessage = ""
      this.responseMessage = "something went wrong!"
    }
  }

}
