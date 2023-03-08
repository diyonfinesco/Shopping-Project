import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: []
})
export class ProductListComponent {
  @Input() products!: Product[];
  @Output() onDeleteProduct: EventEmitter<void> = new EventEmitter();

  constructor() {

  }
}
