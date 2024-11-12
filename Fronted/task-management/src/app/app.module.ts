import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GestionTareasComponent } from './components/gestion-tareas/gestion-tareas.component';
import { PrincipalComponent } from './pages/principal/principal.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Importa estos módulos
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { MessagesModule } from 'primeng/messages';
import { ToastModule } from 'primeng/toast';
import { TagModule } from 'primeng/tag';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { ToolbarModule } from 'primeng/toolbar';
import { CalendarModule } from 'primeng/calendar';
import { LoadingComponent } from './components/loading/loading.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { InterruptorLoadingService } from 'src/service/interruptor-loading.service';
import { NavbarComponent } from './components/navbar/navbar.component';



@NgModule({
  declarations: [
    AppComponent,
    GestionTareasComponent,
    PrincipalComponent,
    LoadingComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    BrowserAnimationsModule,
    TableModule,
    ReactiveFormsModule,
    ButtonModule,
    DialogModule ,
    MessagesModule,
    ToastModule,
    TagModule,
    InputTextModule,
    InputTextareaModule,
    DropdownModule,
    HttpClientModule,
    ToolbarModule,
    CalendarModule,
    FormsModule
  ],
  providers: [MessageService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterruptorLoadingService,
      multi: true
    },
    { provide: LocationStrategy, useClass: HashLocationStrategy },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
