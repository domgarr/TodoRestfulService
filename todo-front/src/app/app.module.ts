import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//Remember to add the HttpClientModule in imports to make it accessible everywhere.
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { TodoComponent } from './todo/todo.component';
import { TodolistComponent } from './todolist/todolist.component';

@NgModule({
  declarations: [
    AppComponent,
    TodoComponent,
    TodolistComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
