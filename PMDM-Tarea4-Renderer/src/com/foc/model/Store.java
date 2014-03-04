package com.foc.model;

import java.util.ArrayList;

public interface Store{
	
	public ArrayList<ProductType> getList();
	
	public ArrayList<ProductType> getPositions(ArrayList<Integer> positions);
	
	public void addProduct(ProductType... products);
	
	public void addProduct(ArrayList<ProductType> products);

	public void addProduct(String name, double price, String description, String icon);

	public void updateProduct(ProductType updatedProduct);

	public void remove(int code);

	public void remove(ArrayList<Integer> productPositions);

	public ProductType findProduct(int productCode);

	public int getCategoryIndex(String category);

}