import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-t-shop-details',
  templateUrl: './t-shop-details.component.html',
  styleUrls: []
})
export class TShopDetailsComponent implements OnInit {
  product!: Product;
  quantity = 1;

  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get("productId")!
    this.productService.readProduct(id).subscribe((data) => {
      this.product = data
    })
  }

  onIncrement() {
    if (this.quantity >= this.product.quantity) return;
    this.quantity++;
  }

  onDecrement() {
    if (this.quantity <= 1) return;
    this.quantity--;
  }

  onAddToCart() {
    this.cartService.addToCart(this.product, this.quantity)
  }

  onProductExist() {
    return this.cartService.isProductExist(this.product)
  }

}
