package com.alatest.routercall;

import static com.alatest.routercall.Operator.OperatorBuilder.operator;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperatorTest {
	
	private static Operator operator;
	
	
	@BeforeClass
	public static void setUp(){
		operator = operator("Operator A")
				.add(new Tax("1", 0.9d))
				.add(new Tax("268", 5.1d))
				.add(new Tax("4631", 0.15d))
				.add(new Tax("46", 0.17d))
				.add(new Tax("4620", 0.0d))
				.add(new Tax("46732", 1.1d))
				.add(new Tax("468", 0.15d))
				.add(new Tax("4673", 0.9d))
				.build();
	}
	
	@Test
	public void souldReturnTheLargestPrefix(){
		
		Tax tax = operator.getCheapestPrice("4673212345");
		
		assertEquals("Operator should return the largest prefix","46732", tax.getPrefix());
		assertEquals("Operator should return the correct price",Double.valueOf(1.1), tax.getPrice());
		
	}
	
	@Test
	public void souldReturnTheSmallestPrefix(){
		
		Tax tax = operator.getCheapestPrice("4613212345");
		
		assertEquals("Operator should return the smallest prefix","46", tax.getPrefix());
		assertEquals("Operator should return the correct price",Double.valueOf(0.17), tax.getPrice());
		
	}
	
	@Test
	public void shouldNotMatchAnyPrefix(){
		
		Tax tax = operator.getCheapestPrice("4173212345");
		
		assertNull("Operator should not return any Tax", tax);
	}
	
	@Test
	public void taxListShouldBeSortedDescendingByPrefixLengh() {
		
		Tax nextTax = null;
		Tax currentTax = null;
		for(int index = 0 ; index < operator.getTaxList().size() -1 ; index ++){
			
			nextTax = operator.getTaxtAt(index+1);
			currentTax = operator.getTaxtAt(index);
			assertTrue("Tax list is not sorted",currentTax.getPrefix().length() >= nextTax.getPrefix().length());
			
		}
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldNotCreateOperatorWithoutTaxs() {
	    operator("Invalid Operator").build();
	}
}
