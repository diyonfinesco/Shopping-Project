import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
})
export class OrderComponent implements OnInit {
  orders!: Order[];

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.getOrders()
  }

  getOrders() {
    this.orderService.getOrders().subscribe((data) => this.orders = data)
  }

  updateOrder(id: string) {
    this.orderService.updateOrder(id).subscribe(() => this.getOrders())
  }
}
