import { Component, Input } from '@angular/core';
import { Product } from 'src/app/shared/product/product';

@Component({
  selector: '[app-product-details]',
  templateUrl: './product-details.component.html',
  styleUrls: []
})
export class ProductDetailsComponent {
  @Input() product!: Product;
}
