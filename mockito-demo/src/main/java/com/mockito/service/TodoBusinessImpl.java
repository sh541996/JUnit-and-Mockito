package com.mockito.service;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl  {
	
	private TodoService todoService; 
	

	public TodoBusinessImpl(TodoService todoService) {
		this.todoService = todoService;
	}

	public List<String> retriveTodosRelatedToSpring(String user) {
		
		List<String> filterTodos = new ArrayList<String>();
		List<String> todos = todoService.retriveTodos(user);
	
		for(String todo:todos) {
			
			if(todo.contains("spring")) filterTodos.add(todo);
		}
		return filterTodos;
	}
	
	public void deleteTodosNotRelatedToSpring(String user) {
		
		List<String> filterTodos = new ArrayList<String>();
		List<String> todos = todoService.retriveTodos(user);
	
		for(String todo:todos) {
			
			if(!todo.contains("spring")) todoService.deleteTodo(todo);
		}
	}


}
