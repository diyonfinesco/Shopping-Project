import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, retry, catchError } from 'rxjs';
import { environment } from 'src/environment';
import { Product } from '../shared/product/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiURL = `${environment.apiUrl}/products`

  constructor(private http: HttpClient) { }

  public createProduct(product: Product) {

  }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiURL)
  }

  public updateProduct(product: Product) {

  }

  public deleteProduct(id: string) {

  }
}
