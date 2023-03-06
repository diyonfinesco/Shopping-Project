import { EventEmitter, Injectable, OnInit, Output } from '@angular/core';
import { Product } from '../shared/product/product';

interface Item {
  product: Product,
  quantity: number
}

@Injectable({
  providedIn: 'root'
})
export class CartService implements OnInit {
  constructor() { }
  items: Item[] = []

  ngOnInit(): void {
    localStorage.setItem("items", JSON.stringify(this.items))
  }

  addToCart(product: Product) {
    const data = localStorage.getItem('items');

    if (data !== null) {
      let products: Product[] = JSON.parse(data)
      products.findIndex(p => p.id == product.id) == -1 && products.push(product)
      localStorage.setItem("items", JSON.stringify(products))
    } else {
      localStorage.setItem("items", JSON.stringify([product]))
    }
  }
}
