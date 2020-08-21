package com.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void mockList() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2);
		assertEquals(2, mockList.size());
	}
	
	@Test
	public void mockListReturnMultipleValues() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, mockList.size());
		assertEquals(3, mockList.size());
	}
	
	@Test
	public void mockListGet() {
		List mockList = mock(List.class);
		// Argument matchers
		when(mockList.get(anyInt())).thenReturn("shubham");
		assertEquals("shubham", mockList.get(0));
		assertEquals("shubham", mockList.get(10));
		
	}
	
	@Test(expected=RuntimeException.class)
	public void mockListThrowAnException() {
		List mockList = mock(List.class);
		// Argument matchers
		when(mockList.get(anyInt())).thenThrow(new RuntimeException("something"));
		mockList.get(0);
		
	}

}
