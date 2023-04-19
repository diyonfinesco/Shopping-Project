import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-t-shop',
  templateUrl: './t-shop.component.html',
  styleUrls: []
})
export class TShopComponent implements OnInit {
  products: Product[] = []
  page: number = 0;
  size: number = 0;
  totalItems: number = 0;

  constructor(private productService: ProductService, private userService: UserService) { }

  ngOnInit() {
    this.fetchProducts()
  }

  onCategoryChange = (category: string) => {
    this.fetchProducts(this.page, category)
  }

  fetchProducts(page = 1, category = "") {
    this.productService.getProducts(page, category).subscribe((data) => {
      this.products = data.products
      this.page = data.page
      this.size = data.size
      this.totalItems = data.totalItems
    })
  }

  isAuthenticated() {
    return this.userService.isAuthenticated()
  }

  isAdmin() {
    return this.userService.isAdmin()
  }

  onDelete(id: string) {
    this.productService.deleteProduct(id).subscribe(() => this.fetchProducts())

  }
}
