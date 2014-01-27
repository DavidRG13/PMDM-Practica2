package com.foc.model;

import java.util.ArrayList;

public interface Store<T>{
	
	public abstract ArrayList<T> getList();
	
	public abstract void addProduct(T object);

	public abstract void addProduct(String name, double price, String description, String icon);

	public abstract void updateProduct(T updatedProduct);

	public abstract void removeProduct(int i);

	public abstract Product findProduct(int productCode);

	public abstract int getCategoryIndex(String category);

}