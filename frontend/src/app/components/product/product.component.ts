import { Component } from '@angular/core';
import { Product } from 'src/app/models';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html'
})
export class ProductComponent {
  products: Product[] = []
  page: number = 0
  size: number = 0
  totalItems: number = 0;

  constructor(public productService: ProductService, private userService: UserService) { }

  ngOnInit() {
    this.fetchProducts()
  }

  isAdmin() {
    return this.userService.isAdmin()
  }

  fetchProducts(page = 1) {
    this.productService.getProducts(page).subscribe((data) => {
      this.products = data.products
      this.page = data.page
      this.size = data.size
      this.totalItems = data.totalItems
    })
  }
}
