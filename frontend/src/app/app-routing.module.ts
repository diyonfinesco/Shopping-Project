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
import { THomeComponent } from './templates/t-home/t-home.component';
import { TShopComponent } from './templates/t-shop/t-shop.component';
import { TShopDetailsComponent } from './templates/t-shop-details/t-shop-details.component';
import { TCartComponent } from './templates/t-cart/t-cart.component';
import { TLoginComponent } from './templates/t-login/t-login.component';
import { TAboutComponent } from './templates/t-about/t-about.component';
import { TRegisterComponent } from './templates/t-register/t-register.component';


const routes: Routes = [
  { path: '', pathMatch: "full", component: THomeComponent },
  { path: 'products', component: TShopComponent },
  { path: 'products/:productId', component: TShopDetailsComponent },
  { path: 'orders', component: OrderComponent },
  { path: 'users', component: UserComponent },
  { path: 'login', component: TLoginComponent },
  { path: 'register', component: TRegisterComponent },
  { path: 'cart', component: TCartComponent },
  { path: 'about', component: TAboutComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
