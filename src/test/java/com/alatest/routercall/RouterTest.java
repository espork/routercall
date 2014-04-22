package com.alatest.routercall;

import static com.alatest.routercall.Operator.OperatorBuilder.operator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.function.BiFunction;

import org.junit.BeforeClass;
import org.junit.Test;

import com.alatest.routercall.Router.Result;

public class RouterTest {
	
	private static Operator operatorA;
	private static Operator operatorB;
	
	
	@BeforeClass
	public static void setUp(){
		
		BiFunction<String, Double, Tax> taxFactory = Tax::new;
		
		operatorA = operator("Operator A")
			.add(taxFactory.apply("1", 0.99d))
			.add(taxFactory.apply("268", 5.1d))
			.add(taxFactory.apply("46", 0.17d))
			.add(taxFactory.apply("4620", 0.0d))
			.add(taxFactory.apply("468", 0.15d))
			.add(taxFactory.apply("4631", 0.15d))
			.add(taxFactory.apply("4673", 0.9d))
			.add(taxFactory.apply("46732", 1.1d))
			.build();
		
		operatorB = operator("Operator B")
				.add(taxFactory.apply("1", 0.92d))
				.add(taxFactory.apply("44", 0.5d))
				.add(taxFactory.apply("46", 0.2d))
				.add(taxFactory.apply("467", 1.0d))
				.add(taxFactory.apply("48", 1.2d))
				.build();
	}
	
	@Test
	public void shouldMatchLargestPrefixAtTheSameOperator() {
		
		Result result = Router.including(operatorA).dial("4673212345");
		 											
		assertEquals(Double.valueOf(1.1d), result.getCheapestPrice());
		assertEquals("46732", result.getMatchedPrefix());
		assertEquals("Operator A", result.getOperatorName());
		
		
	}
	
	
	@Test
	public void shouldMatchLargestPrefixIncludingManyOperators(){
		
		Result result = Router.including(operatorA,operatorB).dial("4673212345");
		
		assertEquals(Double.valueOf(1.1d), result.getCheapestPrice());
		assertEquals("46732", result.getMatchedPrefix());
		assertEquals("Operator A", result.getOperatorName());
		
	}
	
	
	@Test
	public void shouldNotMatchNothing(){
		
		Result result = Router.including(operatorA,operatorB).dial("4173212345");
		
		assertNull("Should not match nothing", result);
	}
	
	@Test
	public void shouldMatchCheapestPrice(){
		
		Result result = Router.including(operatorA,operatorB).dial("1673212345");
		
		assertEquals(Double.valueOf(0.92d), result.getCheapestPrice());
		assertEquals("1", result.getMatchedPrefix());
		assertEquals("Operator B", result.getOperatorName());
		
	}
	
	
	@Test
	public void shouldMatchSmallestPrefix() {
		
		Result result = Router.including(operatorA,operatorB).dial("4613212345");
			
		assertEquals(Double.valueOf(0.17d), result.getCheapestPrice());
		assertEquals("46", result.getMatchedPrefix());
		assertEquals("Operator A", result.getOperatorName());
	}

}
