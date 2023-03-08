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

  constructor(public productService: ProductService, private userService: UserService) { }

  ngOnInit() {
    this.fetchProducts()
  }

  isAdmin() {
    return this.userService.isAdmin()
  }

  fetchProducts() {
    this.productService.getProducts().subscribe((data) => this.products = data)
  }
}
