import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiURL = "http://localhost:8080/users"
  constructor(private http: HttpClient) { }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiURL + "/register", user).pipe(retry(1), catchError(this.handleError))
  }

  loginUser(username: string, password: string) {
    return this.http.post<{ username: string, password: string }>(this.apiURL + "/login", { username, password }).pipe(retry(1), catchError(this.handleError))
  }

  private handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(() => {
      return errorMessage;
    });
  }
}
