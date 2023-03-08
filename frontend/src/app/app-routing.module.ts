import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProductComponent } from './components/product/product.component';
import { HomeComponent } from './components/home/home.component';
import { ProductUpdateComponent } from './components/product/product-update/product-update.component';
import { OrderComponent } from './components/order/order.component';
import { UserComponent } from './components/user/user.component';


const routes: Routes = [
  { path: '', pathMatch: "full", component: HomeComponent },
  { path: 'products', component: ProductComponent },
  { path: 'products/:productId', component: ProductUpdateComponent },
  { path: 'orders', component: OrderComponent },
  { path: 'users', component: UserComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'cart', component: CartComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
