package com.alatest.routercall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operator {

	private String name;
	private List<Tax> taxList = new ArrayList<Tax>();
	
	private Operator(String name){
		this.name = name;
	}
	
	public List<Tax> getTaxList() {
		return taxList;
	}
	
	public String getName() {
		return name;
	}
	
	
	public static class OperatorBuilder{
		
		private Operator operator;
		private OperatorBuilder(String name){
			this.operator = new Operator(name);
		}
		
		public static OperatorBuilder operator(String name) {
			return new OperatorBuilder(name);
		}
		
		public OperatorBuilder add(Tax tax) {
			
			this.operator.getTaxList().add(tax);
			Collections.sort(this.operator.getTaxList());
			
			return this;
		}
		
		public Operator build(){
			return this.operator;
		}
		
	}


	public Tax getChepeastPrice(String phoneNumber) {
		
		// fazer o match para pegar a menor taxa c  maior prefixo
			
		return null;
	}
	
}
