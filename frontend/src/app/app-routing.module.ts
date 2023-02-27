import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewProductsComponent } from './view-products/view-products.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
  { path: '', pathMatch: "full", component: ViewProductsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', pathMatch: "full", component: RegisterComponent },
  { path: 'cart', pathMatch: "full", component: CartComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
