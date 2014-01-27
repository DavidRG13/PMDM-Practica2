package com.foc.model;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

public class ProductStore implements Store<Product>, Serializable {
	
	private static final long serialVersionUID = 2703901348158894622L;
	private static final ProductStore instancia = new ProductStore();
	private ArrayList<Product> lista;
	
	public static synchronized ProductStore getStore(){
		return instancia;
	}
	
	private ProductStore() {
		lista = new ArrayList<Product>();
		lista.add(new Product(1,"producto 1", 12.3, "descripcion 1", "comida"));
		lista.add(new Product(2,"producto 2", 12.3, "descripcion 2", "bebida"));
		lista.add(new Product(3,"producto 3", 12.3, "descripcion 3", "limpieza"));
		lista.add(new Product(4,"producto 4", 12.3, "descripcion 4", "dulces"));
	}
	
	public ArrayList<Product> getList() {
		return lista;
	}
	
	@Override
	public void addProduct(Product product){
		lista.add(product);
	}
	
	@Override
	public void addProduct(String name, double price, String description, String icon){
		lista.add(new Product(getCodeForANewProduct(), name, price, description, icon));
		Log.e("AQUIIII", "tamaño lista "+ lista.size());
		Log.e("AQUIIII", "instancia "+ this.toString());
	}
	
	@Override
	public void updateProduct(Product updatedProduct){
		//TODO funciona sin implementarlo?????
	}
	
	@Override
	public void removeProduct(int code){
		lista.remove(findProduct(code));
	}
	
	@Override
	public Product findProduct(int productCode) {
		for(Product actualProduct : lista){
			if(actualProduct.getCode() == productCode)
				return actualProduct;
		}
		return null;
	}
	
	@Override
	public int getCategoryIndex(String category){
		if(category.equals("comida"))
			return 0;
		if(category.equals("bebida"))
			return 1;
		if(category.equals("limpieza"))
			return 2;
		if(category.equals("dulces"))
			return 3;
		return -1;
	}
	
	private int getCodeForANewProduct(){
		for(int i = 1; i < 1000; i++)
			if(findProduct(i) == null)
				return i;
		return -1;
	}

}