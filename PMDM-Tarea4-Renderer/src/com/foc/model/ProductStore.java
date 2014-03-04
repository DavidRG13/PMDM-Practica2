package com.foc.model;

import java.util.ArrayList;

public class ProductStore extends StoreList{
	
	private static final ProductStore instancia = new ProductStore();
	
	public static synchronized ProductStore getStore(){
		return instancia;
	}
	
	private ProductStore() {
		lista = new ArrayList<ProductType>();
		lista.add(new General_Product(new Product(1,"producto 1", 12.3, "descripcion 1", "comida")));
		lista.add(new General_Product(new Product(2,"producto 2", 12.3, "descripcion 2", "bebida")));
		lista.add(new General_Product(new Product(3,"producto 3", 12.3, "descripcion 3", "limpieza")));
		lista.add(new General_Product(new Product(4,"producto 4", 12.3, "descripcion 4", "dulces")));
	}
	
	@Override
	public void addProduct(String name, double price, String description, String icon){
		lista.add(new General_Product(new Product(getCodeForANewProduct(), name, price, description, icon)));
	}

}