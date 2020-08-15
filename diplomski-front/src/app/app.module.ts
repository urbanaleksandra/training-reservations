import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { LoginService } from './service/login.service';
import { CardComponent } from './card/card.component';
import { ConfirmAccountComponent } from './confirm-account/confirm-account.component';
import { NewTrainingDialogComponent } from './new-training-dialog/new-training-dialog.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { NgxQRCodeModule } from 'ngx-qrcode2';
import { AnQrcodeModule } from 'an-qrcode';
import { ConfirmArrivalComponent } from './confirm-arrival/confirm-arrival.component';
import { ZXingScannerModule } from "@zxing/ngx-scanner";

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    RegistrationComponent,
    CardComponent,
    ConfirmAccountComponent,
    NewTrainingDialogComponent,
    UserProfileComponent,
    ConfirmArrivalComponent
  ],
  entryComponents: [NewTrainingDialogComponent, ConfirmArrivalComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    NgxQRCodeModule,
    AnQrcodeModule,
    ZXingScannerModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  },
    LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
