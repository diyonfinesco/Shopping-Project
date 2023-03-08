import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environment';
import { Item } from '../models';
import { Order } from '../models/order';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiURL = `${environment.apiUrl}/orders`

  constructor(private http: HttpClient, private userService: UserService) { }

  createOrder(order: Item[]) {
    return this.http.post<Item[]>(this.apiURL, { items: [...order] }, { headers: this.userService.getHeaders() })
  }

  getOrders() {
    return this.http.get<Order[]>(this.apiURL, { headers: this.userService.getHeaders() })
  }

  updateOrder(id: string) {
    return this.http.put<Order>(`${this.apiURL}/${id}`, { status: "SHIPPED" }, { headers: this.userService.getHeaders() })
  }
}
