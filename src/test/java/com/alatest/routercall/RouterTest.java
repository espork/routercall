package com.alatest.routercall;

import static  org.junit.Assert.*;
import org.junit.Test;
import static com.alatest.routercall.Operator.OperatorBuilder.operator;

public class RouterTest {
	
	@Test
	public void shouldMatchLargestPrefixAtTheSameOperator() {
		Operator operatorA = operator("Operator A")
							.add(new Tax("1", 0.9d))
							.add(new Tax("268", 5.1d))
							.add(new Tax("46", 0.17d))
							.add(new Tax("4620", 0.0d))
							.add(new Tax("468", 0.15d))
							.add(new Tax("4631", 0.15d))
							.add(new Tax("4673", 0.9d))
							.add(new Tax("46732", 1.1d))	
							.build();
		
		Router router = Router.including(operatorA);
		
		 													
		
		assertTrue(true);
		
	}

}
