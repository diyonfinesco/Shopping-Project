import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Item, Product } from 'src/app/models';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-t-cart',
  templateUrl: './t-cart.component.html',
  styleUrls: []
})
export class TCartComponent {
  items: Item[] = [];
  total = 0;

  constructor(private cartService: CartService, private orderService: OrderService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    if (this.userService.isAdmin()) {
      this.router.navigateByUrl('/')
    }
    this.onReadCart()
  }

  onReadCart() {
    this.items = this.cartService.getCart()
    this.calculateTotal()
  }

  onQuantityChange(id: string, quantity: number) {
    this.items = this.items.map((i) => i.product.id === id ? { product: { ...i.product }, quantity } : i)
    this.onUpdate()
  }

  onUpdate() {
    this.cartService.updateCart(this.items)
    this.calculateTotal()
  }

  onDelete(product: Product) {
    this.cartService.deleteFromCart(product.id)
    this.onReadCart()
  }

  onPlaceOrder() {
    this.onReadCart()
    if (!this.userService.isAuthenticated()) {
      this.router.navigateByUrl('/login')
      return;
    }
    this.orderService.createOrder(this.items).subscribe(() => this.cartService.clearCart())
    this.router.navigateByUrl('/')
  }

  private calculateTotal() {
    this.total = 0
    this.items.forEach((i) => this.total += (i.product.price * i.quantity))
  }
}
