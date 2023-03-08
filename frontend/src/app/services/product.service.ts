import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment';
import { Product } from '../models/product';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiURL = `${environment.apiUrl}/products`

  constructor(private http: HttpClient, private userService: UserService) { }

  public createProduct(product: any) {
    return this.http.post<Product>(this.apiURL, product, { headers: this.userService.getHeaders() })
  }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiURL)
  }

  public readProduct(id: String): Observable<Product> {
    return this.http.get<Product>(`${this.apiURL}/${id}`)
  }

  public updateProduct(id: String, product: any) {
    return this.http.put<Product>(`${this.apiURL}/${id}`, product, { headers: this.userService.getHeaders() })
  }

  public deleteProduct(id: string) {
    return this.http.delete<Product>(`${this.apiURL}/${id}`, { headers: this.userService.getHeaders() })
  }
}
