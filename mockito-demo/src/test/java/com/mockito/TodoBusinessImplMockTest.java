package com.mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.mockito.service.TodoBusinessImpl;
import com.mockito.service.TodoService;

public class TodoBusinessImplMockTest {
	


	@Test
	public void testRetriveTodosRelatedToSpring_usingAStub() {
		
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
		when(todoServiceMock.retriveTodos("Dummy")).thenReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetriveTodosRelatedToSpring_usingBDD() {
		
		// given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
//		when(todoServiceMock.retriveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		// when
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");
		
		//then
//		assertEquals(2, filteredTodos.size());
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void testDeleteTodosRelatedNotToSpring_usingBDD() {
		
		// given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then
		verify(todoServiceMock).deleteTodo("learn to dance");
		verify(todoServiceMock, atLeast(1)).deleteTodo("learn to dance");
		verify(todoServiceMock, times(1)).deleteTodo("learn to dance");
		verify(todoServiceMock, never()).deleteTodo("Learn spring Mvc");
		
		// BBD
		then(todoServiceMock).should().deleteTodo("learn to dance");
		then(todoServiceMock).should(never()).deleteTodo("Learn spring Mvc");

	}
	
	@Test
	public void testDeleteTodosRelatedNotToSpring_usingBDD_ArgumentCapture() {
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		// given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then		
		then(todoServiceMock).should().deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(),is("learn to dance"));
		
	}
	
	@Test
	public void testDeleteTodosRelatedNotToSpring_usingBDD_ArgumentCapture_TwoTimes() {
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		// given
		TodoService todoServiceMock = mock(TodoService.class);
		
		List<String> todos = Arrays.asList("Learn to rock and roll",
				"learn spring",
				"learn to dance");
		
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then		
		then(todoServiceMock).should(times(2)).deleteTodo(argumentCaptor.capture());
//		assertThat(argumentCaptor.getValue(),is("learn to dance"));
		assertThat(argumentCaptor.getAllValues().size(),is(2));
		
	}



}
