import { Component } from '@angular/core';
import { environment } from 'src/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
})
export class HomeComponent {
  imageURL = environment.imageUrl
}
