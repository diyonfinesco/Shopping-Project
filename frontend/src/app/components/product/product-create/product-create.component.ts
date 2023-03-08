import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html'
})
export class ProductCreateComponent {
  constructor(private productService: ProductService) { }
  @Output() onCreateProduct: EventEmitter<void> = new EventEmitter();

  productForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(20)]),
    description: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    category: new FormControl('', [Validators.required, Validators.maxLength(15)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)])
  })

  onSubmit() {
    this.productService.createProduct(this.productForm.value).subscribe((data) => this.onCreateProduct.emit())
  }
}
