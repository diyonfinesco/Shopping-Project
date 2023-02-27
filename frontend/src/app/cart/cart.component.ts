import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../shared/product/product';
import { OrderService } from '../shared/order/order.service';

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
  constructor(private router: Router, private restApi: OrderService) { }

  items: Item[] = [];

  ngOnInit() {
    const data = localStorage.getItem('items');
    if (data !== null) {
      const products: Product[] = JSON.parse(data)
      products.forEach((p) => this.items.push({ product: p, quantity: 1 }))
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
    }
  }

  onHomeClick() {
    this.router.navigate([''])
  }
}
