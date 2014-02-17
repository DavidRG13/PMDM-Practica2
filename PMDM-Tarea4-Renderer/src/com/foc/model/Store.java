package com.foc.model;

import java.util.ArrayList;

public interface Store{
	
	public abstract ArrayList<ProductType> getList();
	
	public abstract void addProduct(ProductType object);

	public abstract void addProduct(String name, double price, String description, String icon);

	public abstract void updateProduct(ProductType updatedProduct);

	public abstract void removeProduct(int i);

	public abstract ProductType findProduct(int productCode);

	public abstract int getCategoryIndex(String category);
	
	public int getCodeForANewProduct();

	void remove(ArrayList<Integer> productPositions);

}