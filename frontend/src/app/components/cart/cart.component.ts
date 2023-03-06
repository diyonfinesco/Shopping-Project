import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/shared/order/order.service';
import { Product } from 'src/app/shared/product/product';


export interface Item {
  product: Product,
  quantity: number
}
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: []
})
export class CartComponent {
  constructor(private router: Router, private restApi: OrderService, private cartService: CartService) { }

  items: Item[] = [];
  total = 0;
  @Output() btnClick: EventEmitter<Item> = new EventEmitter();

  ngOnInit() {
    this.cartService.ngOnInit()
    const data = localStorage.getItem('items');
    if (data !== null) {
      const products: Product[] = JSON.parse(data)
      products.forEach((p) => this.items.push({ product: p, quantity: 1 }))
      this.calculateTotal()
    }
  }

  onPlaceOrder() {
    const token = localStorage.getItem('token')
    if (token === null)
      this.router.navigate(['login'])
    else {
      this.restApi.createOrder(this.items).subscribe((data) => this.router.navigate(['/']))
    }
  }

  onDelete(id: string) {
    const data = localStorage.getItem('items');

    if (data !== null) {
      let products: Product[] = JSON.parse(data)
      this.items = this.items.filter((i) => i.product.id !== id)
      localStorage.setItem("items", JSON.stringify(products.filter((p) => p.id !== id)))
      this.calculateTotal()
    }
  }

  calculateTotal() {
    this.total = 0
    this.items.forEach((i) => this.total += (i.product.price * i.quantity))
  }

  onHomeClick() {
    this.router.navigate([''])
  }
  onButtonClick() {
    this.btnClick.emit()
  }
}
