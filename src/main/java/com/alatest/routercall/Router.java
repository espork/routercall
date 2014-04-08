package com.alatest.routercall;

import java.util.ArrayList;
import java.util.List;

public class Router {
	
	private List<Operator> operators = new ArrayList<Operator>();
	
	public static Router including(Operator ...operators) {
		return new Router(operators);
	}
	
	private Router (Operator ...operators){
		
		for(Operator operator : operators)
			this.operators.add(operator);
		
	}

	public Result dial(String phoneNumber) {
		
		List<Tax> operatorsPrices = new ArrayList<Tax>();
		
		for(Operator operator : this.operators){
			operatorsPrices.add( operator.getChepeastPrice(phoneNumber) );
		}
		
		return null;
	}
	
	
	public class Result {
		
		private String operatorName;
		private Tax tax;
		
		public String getOperatorName() {
			return operatorName;
		}
		
		public Double getPrice() {
			
			return this.tax.getPrice();
		}
		
		public String getPrefix() {
			return this.tax.getPrefix();
		}
		
	}

}
