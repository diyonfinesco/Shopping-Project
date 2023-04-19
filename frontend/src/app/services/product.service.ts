import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environment';
import { Product } from '../models/product';
import { UserService } from './user.service';
import { CartService } from './cart.service';

interface ProductResponse {
  totalItems: number,
  size: number,
  page: number,
  products: Product[]
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiURL = `${environment.apiUrl}/products`

  constructor(private http: HttpClient, private userService: UserService, private cartService: CartService) { }

  public createProduct(product: any) {
    return this.http.post<Product>(this.apiURL, product, { headers: this.userService.getHeaders() })
  }

  public getProducts(page = 1, category = ""): Observable<ProductResponse> {
    return this.http.get<ProductResponse>(this.apiURL + `?page=${page}&category=${category}`)
  }

  public readProduct(id: String): Observable<Product> {
    return this.http.get<Product>(`${this.apiURL}/${id}`)
  }

  public updateProduct(id: String, product: any) {
    return this.http.put<Product>(`${this.apiURL}/${id}`, product, { headers: this.userService.getHeaders() })
  }

  public deleteProduct(id: string) {
    const response = this.http.delete<Product>(`${this.apiURL}/${id}`, { headers: this.userService.getHeaders() })
    this.cartService.deleteFromCart(id)
    return response;
  }
}
