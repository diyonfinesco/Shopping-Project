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
import { NgxPaginationModule } from 'ngx-pagination';
import { THeaderComponent } from './templates/t-header/t-header.component';
import { TNavComponent } from './templates/t-nav/t-nav.component';
import { TBannerHeroComponent } from './templates/t-banner-hero/t-banner-hero.component';
import { TFooterComponent } from './templates/t-footer/t-footer.component';
import { THomeComponent } from './templates/t-home/t-home.component';
import { TShopComponent } from './templates/t-shop/t-shop.component';
import { TShopDetailsComponent } from './templates/t-shop-details/t-shop-details.component';
import { TCartComponent } from './templates/t-cart/t-cart.component';
import { TLoginComponent } from './templates/t-login/t-login.component';
import { TRegisterComponent } from './templates/t-register/t-register.component';
import { TAboutComponent } from './templates/t-about/t-about.component';

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
    THeaderComponent,
    TNavComponent,
    TBannerHeroComponent,
    TFooterComponent,
    THomeComponent,
    TShopComponent,
    TShopDetailsComponent,
    TCartComponent,
    TLoginComponent,
    TRegisterComponent,
    TAboutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
