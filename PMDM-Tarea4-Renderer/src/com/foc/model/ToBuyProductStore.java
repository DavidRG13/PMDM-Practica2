package com.foc.model;

import java.util.ArrayList;

public class ToBuyProductStore extends StoreList{
	
	private static ToBuyProductStore instancia = new ToBuyProductStore();
	
	public static ToBuyProductStore getStore(){
		return instancia;
	}
	
	private ToBuyProductStore(){
		lista = new ArrayList<ProductType>();
		lista.add(new ToBuy_Product(new Product(3,"producto 3", 12.3, "descripcion 3", "limpieza")));
		lista.add(new ToBuy_Product(new Product(2,"producto 2", 12.3, "descripcion 2", "bebida")));
		lista.add(new ToBuy_Product(new Product(1,"producto 1", 12.3, "descripcion 1", "comida")));
	}
	
	@Override
	public void addProduct(String name, double price, String description, String icon) {
		lista.add(new ToBuy_Product(new Product(getCodeForANewProduct(), name, price, description, icon)));
	}

}