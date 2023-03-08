import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environment';
import { User } from '../models';

interface LoginResponse {
  token: {
    username: string,
    password: string
  },
  user: User
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiURL = `${environment.apiUrl}/users`;

  constructor(private http: HttpClient) { }

  loginUser(username: string, password: string) {
    return this.http.post<LoginResponse>(this.apiURL + '/login', { username, password })
  }

  saveUserAndToken(data: LoginResponse) {
    if (data.user && data.token) {
      localStorage.setItem("user", JSON.stringify(data.user))
      localStorage.setItem("token", JSON.stringify(data.token))
    }
  }

  logout() {
    localStorage.removeItem("user")
    localStorage.removeItem("token")
  }

  getUsers() {
    return this.http.get<User[]>(this.apiURL, { headers: this.getHeaders() })
  }


  getAuthenticatedUser() {
    const data = localStorage.getItem("user");

    if (data) {
      return JSON.parse(data) as User;
    }

    return undefined
  }

  getHeaders() {
    const data = localStorage.getItem("token")!;
    return new HttpHeaders().set('Authorization', 'Basic ' + JSON.parse(data));
  }

  isAuthenticated() {
    return localStorage.getItem("user") !== null && localStorage.getItem("token") !== null;
  }

  isAdmin() {
    return this.getAuthenticatedUser()?.role === "ADMIN";
  }
}
