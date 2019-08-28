import { Component, OnInit } from '@angular/core';
import {Todo} from '../models/todo';

import {TodoService} from '../todo.service';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css']
})
export class TodolistComponent implements OnInit {

  todos : Todo[];

/*
  Upon instantiation Angular will use its DI system to set todoService to a singleton instance of TodoService.
*/
  constructor(private todoService: TodoService) { }

  ngOnInit() {
    console.log(this.todoService);
    this.getTodos();
  }

  getTodos() : void {
    //Revist the => function
    //This statement will wait for Observable to emit an array of todos.
    this.todoService.getTodos().subscribe(todos => this.todos = todos);
  }

}
