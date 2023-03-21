import { AfterViewChecked, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: []
})
export class ProductListComponent  {
  @Input() products!: Product[];
  @Output() onDeleteProduct: EventEmitter<void> = new EventEmitter();
  @Output() onPageChange: EventEmitter<number> = new EventEmitter();
  @Output() onInit: EventEmitter<void> = new EventEmitter();

  @Input() page!: number;
  @Input() size!: number;
  @Input() totalItems!: number;

  constructor() {

  }

  handlePageEvent(event: any){
    this.page = event
    this.onPageChange.emit(event)
  }
}
