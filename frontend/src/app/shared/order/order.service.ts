import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Item } from 'src/app/cart/cart.component';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiURL = "http://localhost:8080/orders"
  constructor(private http: HttpClient) { }

  public createOrder(items: Item[]): Observable<Item[]> {
    const data = localStorage.getItem("token")
    if (data == null)
      throw new Error("error")


    const credentials = JSON.parse(data)

    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(credentials.tokenUser + ':' + credentials.tokenPass)
    });

    return this.http.post<Item[]>(this.apiURL, { items }, { headers }).pipe(retry(1), catchError(this.handleError))
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
