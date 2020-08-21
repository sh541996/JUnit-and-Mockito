package com.junit.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StringHelperTest {
	
	
	//StringHelper stringHelper = new StringHelper(); 
	StringHelper stringHelper;
	
	@Before
	public void before() {
		stringHelper = new StringHelper();
	}


	@Test
	public void testTruncateAInFirst2Positions() {
		//fail("Not yet implemented");
		
		//expected:<ABC[]> but was:<ABC[D]>
		//expected, actual
		//assertEquals("ABC", "ABCD");
		
		//StringHelper stringHelper = new StringHelper(); 
		//assertEquals(expected, actual);
		assertEquals("CD", stringHelper.truncateAInFirst2Positions("AACD"));
		assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));
	}
	
	@Test
	public void testTruncateAInFirst2Positions2() {
		
		//StringHelper stringHelper = new StringHelper(); 
		assertEquals("CD", stringHelper.truncateAInFirst2Positions("ACD"));
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame() {
		//boolean actual = stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD");
		//boolean expected = false;
		//assertEquals(stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"));
		assertFalse(stringHelper.areFirstAndLastTwoCharactersTheSame("ABCD"));
	}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame2() {
		assertTrue(stringHelper.areFirstAndLastTwoCharactersTheSame("ABAB"));
	}
}
