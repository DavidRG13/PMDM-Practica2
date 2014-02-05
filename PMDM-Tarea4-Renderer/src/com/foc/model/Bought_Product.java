package com.foc.model;

public class Bought_Product{
	
	private Product product;

	public Bought_Product() {}
	
	public Bought_Product (Product product){
		this.product = product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Product getProduct() {
		return product;
	}

}