package com.mockito;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.mockito.service.TodoBusinessImpl;
import com.mockito.service.TodoService;

public class TodoBusinessImplStubTest {

	@Test
	public void testRetriveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("dummy");
		assertEquals(2, filteredTodos.size());
	}

}
