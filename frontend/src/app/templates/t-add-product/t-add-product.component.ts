import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-t-add-product',
  templateUrl: './t-add-product.component.html',
  styleUrls: []
})
export class TAddProductComponent {
  constructor(private productService: ProductService) { }
  @Output() onCreateProduct: EventEmitter<void> = new EventEmitter();

  productForm = new FormGroup({
    image: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    description: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    category: new FormControl('', [Validators.required, Validators.maxLength(15)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    quantity: new FormControl(0, [Validators.required, Validators.min(1)])
  })

  toFormData(formValue: any) {
    const formData = new FormData();

    for (const key of Object.keys(formValue)) {
      const value = formValue[key];
      console.log(key, value);

      formData.append(key, value);
    }
    return formData;
  }

  onFileChange(event: any) {

    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.productForm.patchValue({
        image: file
      });
    }
  }

  onSubmit() {
    this.productService.createProduct(this.toFormData(this.productForm.value)).subscribe((data) => this.productForm.reset())
  }
}