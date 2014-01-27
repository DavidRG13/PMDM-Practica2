package com.foc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class BoughtProductStore implements Store<Bought_Product>, Serializable{
	
	private static BoughtProductStore instancia = new BoughtProductStore();
	private ArrayList<Bought_Product> lista;
	
	public static BoughtProductStore getStore(){
		return instancia;
	}
	
	private BoughtProductStore(){
		lista = new ArrayList<Bought_Product>();
		lista.add(new Bought_Product(4,"producto 4", 12.3, "descripcion 4", "dulces"));
	}
	
	public ArrayList<Bought_Product> getList() {
		return lista;
	}

	@Override
	public void addProduct(Bought_Product product) {
		lista.add(product);
	}

	@Override
	public void addProduct(String name, double price, String description, String icon) {
		lista.add(new Bought_Product(getCodeForANewProduct(), name, price, description, icon));
	}

	@Override
	public void updateProduct(Bought_Product updatedProduct) {
		//TODO funciona sin implementarlo?????
	}

	@Override
	public void removeProduct(int code) {
		lista.remove(findProduct(code));
	}

	@Override
	public Bought_Product findProduct(int productCode) {
		for(Bought_Product actualProduct : lista){
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
