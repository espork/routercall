package com.alatest.routercall;

public class Tax implements Comparable<Tax> {
	
	
	private String prefix;
	private Double price;
	
	public Tax(String prefix, Double price) {
		if(prefix == null || price == null) throw new RuntimeException("Prefix and Price cannot be null");
		this.prefix = prefix;
		this.price = price;
	}
	
	
	public String getPrefix() {
		return prefix;
	}
	
	
	public Double getPrice() {
		return price;
	}


	public int compareTo(Tax other) {
		return Integer.valueOf(this.prefix.length()).compareTo(other.prefix.length()) ;
	}
}
