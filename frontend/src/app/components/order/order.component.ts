import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
})
export class OrderComponent implements OnInit {
  orders!: Order[];

  constructor(private orderService: OrderService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    if (!this.userService.isAdmin()) {
      this.router.navigateByUrl('/')
    }
    this.getOrders()
  }

  getOrders() {
    this.orderService.getOrders().subscribe((data) => this.orders = data)
  }

  updateOrder(id: string) {
    this.orderService.updateOrder(id).subscribe(() => this.getOrders())
  }
}
