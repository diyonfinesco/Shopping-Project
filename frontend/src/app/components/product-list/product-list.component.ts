import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/shared/product/product';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: []
})
export class ProductListComponent {
  products: Product[] = []

  constructor(public restAPI: ProductService, private router: Router) { }

  ngOnInit() {
    this.fetchProducts()
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

  private async fetchProducts(): Promise<void> {
    this.restAPI.getProducts().subscribe((data) => this.products = data)
  }
}
