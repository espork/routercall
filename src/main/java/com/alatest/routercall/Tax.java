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
	
	public int getPrefixLengh(){
		return this.prefix.length();
	}
	
	
	public Double getPrice() {
		return price;
	}
	
	public boolean lessThan(Tax other) {
		return this.getPrice().compareTo( other.getPrice() ) < 0;
	}
	
	public boolean prefixGreaterOrEqual(Tax other) {
		return this.compareTo(other) <=0;
	}

	public int compareTo(Tax other) {
		return Integer.valueOf(other.prefix.length()).compareTo(this.prefix.length()) ;
	}
	
	public String toString() {
		return "[ prefix :" + prefix + " --- price : " + price.toString() + " ]";
	}
}
