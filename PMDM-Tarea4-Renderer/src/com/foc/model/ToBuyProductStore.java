package com.foc.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ToBuyProductStore implements Store<ToBuy_Product>, Serializable{
	
	private static ToBuyProductStore instancia = new ToBuyProductStore();
	private ArrayList<ToBuy_Product> lista;
	
	public static ToBuyProductStore getStore(){
		return instancia;
	}
	
	private ToBuyProductStore(){
		lista = new ArrayList<ToBuy_Product>();
		lista.add(new ToBuy_Product(3,"producto 3", 12.3, "descripcion 3", "limpieza"));
		lista.add(new ToBuy_Product(2,"producto 2", 12.3, "descripcion 2", "bebida"));
		lista.add(new ToBuy_Product(1,"producto 1", 12.3, "descripcion 1", "comida"));
	}
	
	public ArrayList<ToBuy_Product> getList() {
		return lista;
	}

	@Override
	public void addProduct(ToBuy_Product product) {
		lista.add(product);
	}

	@Override
	public void addProduct(String name, double price, String description, String icon) {
		lista.add(new ToBuy_Product(getCodeForANewProduct(), name, price, description, icon));
	}

	@Override
	public void updateProduct(ToBuy_Product updatedProduct) {
		//TODO funciona sin implementarlo?????
	}

	@Override
	public void removeProduct(int code) {
		lista.remove(findProduct(code));
	}

	@Override
	public ToBuy_Product findProduct(int productCode) {
		for(ToBuy_Product actualProduct : lista){
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
