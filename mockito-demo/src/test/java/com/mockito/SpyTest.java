package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void test() {
		List arrayList = mock(ArrayList.class);
		assertEquals(0, arrayList.size());
		//when(arrayList.size()).thenReturn(5);
		arrayList.add("Dummy");
		assertEquals(5, arrayList.size());
	}

	@Test
	public void testSpy() {
		List arrayListSpy = spy(ArrayList.class);
		assertEquals(0, arrayListSpy.size());
		//when(arrayListSpy.size()).thenReturn(5);
		arrayListSpy.add("Dummy");
		assertEquals(5, arrayListSpy.size());
	}

}
