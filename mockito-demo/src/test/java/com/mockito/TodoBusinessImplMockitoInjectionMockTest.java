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

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import com.mockito.service.TodoBusinessImpl;
import com.mockito.service.TodoService;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectionMockTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> argumentCaptor ;

	@Test
	public void testRetriveTodosRelatedToSpring_usingAStub() {
			
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
		when(todoServiceMock.retriveTodos("Dummy")).thenReturn(todos);
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");
		assertEquals(2, filteredTodos.size());
	}
	
	@Test
	public void testRetriveTodosRelatedToSpring_usingBDD() {
		
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
//		when(todoServiceMock.retriveTodos("Dummy")).thenReturn(todos);
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);
		
		// when
		List<String> filteredTodos = todoBusinessImpl.retriveTodosRelatedToSpring("Dummy");
		
		//then
//		assertEquals(2, filteredTodos.size());
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void testDeleteTodosRelatedNotToSpring_usingBDD() {
	
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);

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
		
		List<String> todos = Arrays.asList("Learn spring Mvc",
				"learn spring",
				"learn to dance");
		
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);
		
		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then		
		then(todoServiceMock).should().deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(),is("learn to dance"));
		
	}
	
	@Test
	public void testDeleteTodosRelatedNotToSpring_usingBDD_ArgumentCapture_TwoTimes() {
		
		List<String> todos = Arrays.asList("Learn to rock and roll",
				"learn spring",
				"learn to dance");
		
		given(todoServiceMock.retriveTodos("Dummy")).willReturn(todos);
		
		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//then		
		then(todoServiceMock).should(times(2)).deleteTodo(argumentCaptor.capture());
//		assertThat(argumentCaptor.getValue(),is("learn to dance"));
		assertThat(argumentCaptor.getAllValues().size(),is(2));
		
	}

}
