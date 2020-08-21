package com.junit.helper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ArraysCompareTest {

	//Arrays.sort
	
	@Test
	public void testArraySort() {
		int []numbers = {12,3,4,1};
		int []expected = {1,3,4,12};
		Arrays.parallelSort(numbers);
		//assertEquals(expected, numbers);
		assertArrayEquals(expected, numbers);
	}
	
	@Test(expected=NullPointerException.class)
	public void testArraySort_NullArray() {
		int []numbers = null;
		Arrays.parallelSort(numbers); 
	}
	
	@Test(timeout=100)
	public void testArraySortPerformance() {
		int []numbers = {12,13,4};
		for(int i=0; i<=1000000; i++) {
			
		numbers[0]=0;	
		Arrays.parallelSort(numbers); 
		}
	}
	

}
