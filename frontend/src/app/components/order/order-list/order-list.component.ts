import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Order } from 'src/app/models/order';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
})
export class OrderListComponent {
  @Input() orders!: Order[];
  @Output() onUpdateOrder: EventEmitter<string> = new EventEmitter()
}
