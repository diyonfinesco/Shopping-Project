import { Injectable } from '@angular/core';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  constructor() { }

  addToCart(product: Product) {
    const data = localStorage.getItem('items');

    if (data !== null) {
      const products = JSON.parse(data)
      products.findIndex((item: any) => item.product.id == product.id) == -1 && products.push({ product, quantity: 1 })
      localStorage.setItem("items", JSON.stringify(products))
    } else {
      localStorage.setItem("items", JSON.stringify([{ product, quantity: 1 }]))
    }
  }

  getCart() {
    const data = localStorage.getItem('items');
    return data ? JSON.parse(data) : [];
  }

  updateCart(items: any) {
    localStorage.setItem("items", JSON.stringify(items))
  }

  deleteFromCart(id: string) {
    let items = this.getCart();
    items = items.filter((item: any) => item.product.id !== id)
    this.updateCart(items)
  }

  isProductExist(product: Product) {
    const items = this.getCart();
    return items.find((item: any) => item.product.id === product.id)
  }

  clearCart() {
    localStorage.setItem("items", JSON.stringify([]))
  }
}
