import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-t-edit-product',
  templateUrl: './t-edit-product.component.html',
  styleUrls: []
})
export class TEditProductComponent implements OnInit {
  product!: Product;
  selectedImage: File | null = null;
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {
    if (!this.userService.isAdmin()) {
      this.router.navigateByUrl('/')
    }
    const id = this.route.snapshot.paramMap.get("productId")!

    this.productService.readProduct(id).subscribe((data) => {
      this.product = data
      this.productUpdateForm.setValue({
        name: this.product.name,
        description: this.product.description,
        category: this.product.category,
        price: this.product.price,
        quantity: this.product.quantity,
      })
      this.isLoading = false;
    })
  }

  productUpdateForm = this.formBuilder.group({
    name: new FormControl("", [Validators.required, Validators.maxLength(20)]),
    description: new FormControl("", [Validators.required, Validators.maxLength(50)]),
    category: new FormControl("", [Validators.required, Validators.maxLength(15)]),
    price: new FormControl(0, [Validators.required, Validators.min(1)]),
    quantity: new FormControl(0, [Validators.required, Validators.min(1)])
  })

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.selectedImage = file
    }
  }

  toFormData(formValue: any) {
    const formData = new FormData();
    for (const key of Object.keys(formValue)) {
      const value = formValue[key];
      formData.append(key, value);
    }
    if (this.selectedImage) formData.append("image", this.selectedImage)
    return formData;
  }

  onSubmit() {
    this.productService.updateProduct(this.product.id, this.toFormData(this.productUpdateForm.value)).subscribe((data) => this.router.navigateByUrl("/products"))
  }
}