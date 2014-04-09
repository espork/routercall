package com.alatest.routercall;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TaxTest {
	
	
	
	@Test
	public void shouldComparePrices(){
		
		Tax a = new Tax("1234", 1.1d);
		Tax b = new Tax("12345", 1.3d);
		Tax c = new Tax("123", 1.1d);
		
		assertTrue(a.lessThan(b));
		assertFalse(b.lessThan(a));
		assertFalse(a.lessThan(c));
	}
	
	
	@Test 
	public void shouldComparePrefix(){
		
		Tax a = new Tax("1234", 1.1d);
		Tax b = new Tax("12345", 1.3d);
		Tax c = new Tax("1235", 1.1d);
		
		assertFalse(a.prefixGreaterOrEqual(b));
		assertTrue(a.prefixGreaterOrEqual(c));
		assertTrue(b.prefixGreaterOrEqual(a));
	}
	
	
	@Test(expected=RuntimeException.class)
	public void shouldNotCreateTaxWithoutPrefix() {
	    new Tax(null, 9.0d);
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldNotCreateTaxWithoutPrice() {
	    new Tax("1234", null);
	}

}
