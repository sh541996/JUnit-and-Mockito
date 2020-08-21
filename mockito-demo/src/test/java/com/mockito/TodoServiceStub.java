package com.mockito;

import java.util.Arrays;
import java.util.List;

import com.mockito.service.TodoService;

public class TodoServiceStub implements TodoService {

	public List<String> retriveTodos(String user) {
		return Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
	}

	public void deleteTodo(String todo) {
		// TODO Auto-generated method stub
		
	}

}
