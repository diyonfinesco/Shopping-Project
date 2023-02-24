import { Component } from '@angular/core';
import axios from 'axios'

interface Product {
  id: string,
  name: string,
  description: string,
  category: string,
  price: number,
  quantity: number
}



@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: []
})
export class ViewProductsComponent {
  products: Product[] = []

  ngOnInit() {
    this.fetchProducts()
  }

  clickMe(product: Product) {
    console.log(product)
  }

  private async fetchProducts(): Promise<void> {
    try {
      const { data } = await axios.get<Product[]>("http://localhost:8080/products")
      this.products = data;
    } catch (error) {
      console.log(error)
    }
  }
}
