import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { CartComponent } from '../app/components/cart/cart.component';
import { LoginComponent } from '../app/components/login/login.component';
import { RegisterComponent } from '../app/components/register/register.component';
import { ProductCreateComponent } from './components/product/product-create/product-create.component';
import { ProductComponent } from './components/product/product.component';
import { LinkComponent } from './components/header/link/link.component';
import { HomeComponent } from './components/home/home.component';
import { ProductDetailsComponent } from './components/product/product-details/product-details.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ProductUpdateComponent } from './components/product/product-update/product-update.component';
import { OrderComponent } from './components/order/order.component';
import { OrderListComponent } from './components/order/order-list/order-list.component';
import { UserComponent } from './components/user/user.component';
import { UserListComponent } from './components/user/user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    CartComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    ProductListComponent,
    ProductDetailsComponent,
    ProductCreateComponent,
    ProductComponent,
    LinkComponent,
    HomeComponent,
    ProductUpdateComponent,
    OrderComponent,
    OrderListComponent,
    UserComponent,
    UserListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
