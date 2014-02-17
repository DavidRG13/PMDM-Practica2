package com.foc.model;

import java.io.Serializable;

public class Product  implements Serializable{
	
	private int code;
	private String name;
	private double price;
	private String description;
	private String image;
	
	public Product(){}
	
	public Product(int code){
		this.code = code;
	}
	
	public Product(int code, String name, double price, String description, String image){
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "code: "+ code +", name: "+ name +", price: "+ price;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

}
