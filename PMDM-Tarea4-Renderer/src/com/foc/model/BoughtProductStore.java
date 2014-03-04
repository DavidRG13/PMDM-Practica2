package com.foc.model;

import java.util.ArrayList;

public class BoughtProductStore extends StoreList{
	
	private static BoughtProductStore instancia = new BoughtProductStore();
	
	public static BoughtProductStore getStore(){
		return instancia;
	}
	
	private BoughtProductStore(){
		lista = new ArrayList<ProductType>();
		lista.add(new Bought_Product(new Product(4,"producto 4", 12.3, "descripcion 4", "dulces")));
	}
	
	@Override
	public void addProduct(String name, double price, String description, String icon) {
		lista.add(new Bought_Product(new Product(getCodeForANewProduct(), name, price, description, icon)));
	}

}