package com.alatest.routercall;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

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
	
	public Tax getTaxtAt(int index){
		return this.taxList.get(index);
	}
	
	
	public Tax getCheapestPrice(String phoneNumber) {
		
		Optional<Tax> taxOptional =  taxList.stream().filter( tax -> Pattern.compile("^"+tax.getPrefix()).matcher(phoneNumber).find() ).findFirst();
		
		return taxOptional.orElse(null);
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
			return this;
		}
		
		public Operator build(){
			if(this.operator.getTaxList().isEmpty()) throw new RuntimeException("You cannot build Operator without Taxs");

			this.operator.getTaxList().sort( comparingInt(Tax::getPrefixLengh).reversed() );
			
			return this.operator;
		}
		
	}
	
}
