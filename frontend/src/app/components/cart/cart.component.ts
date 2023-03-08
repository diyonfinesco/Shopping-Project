import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Item } from 'src/app/models/item';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: []
})
export class CartComponent implements OnInit {
  items: Item[] = [];
  total = 0;

  constructor(private cartService: CartService, private orderService: OrderService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.onReadCart()
  }

  onReadCart() {
    this.items = this.cartService.getCart()
    this.calculateTotal()
  }

  onUpdate() {
    this.cartService.updateCart(this.items)
    this.calculateTotal()
  }

  onDelete(product: Product) {
    this.cartService.deleteFromCart(product)
    this.onReadCart()
  }

  onPlaceOrder() {
    this.onReadCart()
    if (!this.userService.isAuthenticated()) {
      this.router.navigateByUrl('/login')
      return;
    }
    this.orderService.createOrder(this.items).subscribe(() => this.cartService.clearCart())
    this.router.navigateByUrl('/products')
  }

  private calculateTotal() {
    this.total = 0
    this.items.forEach((i) => this.total += (i.product.price * i.quantity))
  }
}
