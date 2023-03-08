import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: '[app-product-details]',
  templateUrl: './product-details.component.html',
  styleUrls: []
})
export class ProductDetailsComponent {
  @Input() product!: Product;
  @Output() onDeleteProduct: EventEmitter<void> = new EventEmitter()

  constructor(private cartService: CartService, private productService: ProductService, private userService: UserService) { }

  onAddToCart(product: Product) {
    this.cartService.addToCart(product)
  }

  isProductInCart(product: Product) {
    return this.cartService.isProductExist(product);
  }

  isAdmin() {
    return this.userService.isAdmin()
  }

  onDelete(id: string) {
    this.productService.deleteProduct(id).subscribe(() => this.onDeleteProduct.emit())

  }
}
