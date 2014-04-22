package com.alatest.routercall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Router {
	
	private List<Operator> operators = new ArrayList<Operator>();
	
	public static Router including(Operator ...operators) {
		return new Router(operators);
	}
	
	private Router (Operator ...operators){
		Arrays.asList(operators).forEach( this.operators::add);
	}

	public Result dial(String phoneNumber) {
		
		Tax tax = this.operators.get(0).getCheapestPrice(phoneNumber);
		String operatorName = this.operators.get(0).getName();
		Tax currentTax = null ;
		
		for(int index = 1; index < this.operators.size() ; index++) {
			currentTax = this.operators.get(index).getCheapestPrice(phoneNumber);
			
			if(currentTax !=null && currentTax.prefixGreaterOrEqual(tax) && currentTax.lessThan(tax) ){
				tax = currentTax;
				operatorName = this.operators.get(index).getName();
			}
		}
		
		if(tax!=null) {
			return new Result(operatorName,tax);
		}
		
		return null;
	}
	
	
	public class Result {
		
		private String operatorName;
		private Tax cheapestTax;
		
		private Result(String name,Tax tax){
			this.operatorName = name;
			this.cheapestTax = tax;
		}
		
		public String getOperatorName() {
			return operatorName;
		}
		
		public Double getCheapestPrice() {
			return this.cheapestTax.getPrice();
		}
		
		public String getMatchedPrefix() {
			return this.cheapestTax.getPrefix();
		}
		
	}

}
