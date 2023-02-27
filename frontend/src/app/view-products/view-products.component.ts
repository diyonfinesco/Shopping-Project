import { Component } from '@angular/core';
import { ProductService } from '../shared/product/product.service';
import { Product } from '../shared/product/product'
import { Router } from '@angular/router';


@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.scss']
})
export class ViewProductsComponent {
  products: Product[] = []
  search = ""

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

  onCartClick() {
    this.router.navigate(['/cart'])
  }

  onSearch() {
    this.fetchProducts()
  }

  private async fetchProducts(): Promise<void> {
    try {
      console.log(this.search)
      this.restAPI.getProducts(this.search).subscribe((data) => this.products = data)
    } catch (error) {
      console.log(error)
    }
  }

  getTime() {
    return "ok"
  }
}
