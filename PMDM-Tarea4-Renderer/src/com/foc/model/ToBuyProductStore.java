package com.foc.model;

import java.util.ArrayList;

import android.util.Log;

public class ToBuyProductStore implements Store{
	
	private static ToBuyProductStore instancia = new ToBuyProductStore();
	private ArrayList<ProductType> lista;
	
	public static ToBuyProductStore getStore(){
		return instancia;
	}
	
	private ToBuyProductStore(){
		lista = new ArrayList<ProductType>();
		lista.add(new ToBuy_Product(new Product(3,"producto 3", 12.3, "descripcion 3", "limpieza")));
		lista.add(new ToBuy_Product(new Product(2,"producto 2", 12.3, "descripcion 2", "bebida")));
		lista.add(new ToBuy_Product(new Product(1,"producto 1", 12.3, "descripcion 1", "comida")));
	}
	
	public ArrayList<ProductType> getList() {
		return lista;
	}

	@Override
	public void addProduct(ProductType product) {
		lista.add(product);
	}

	@Override
	public void addProduct(String name, double price, String description, String icon) {
		lista.add(new ToBuy_Product(new Product(getCodeForANewProduct(), name, price, description, icon)));
	}

	@Override
	public void updateProduct(ProductType updatedProduct) {
		for(ProductType p : lista)
			if(p.getProductCode() == updatedProduct.getProductCode()){
				Log.d("AQUII", "producto encontrado");
				Log.d("antes", p.getProduct().toString());
				p.setProduct(updatedProduct.getProduct());
				Log.d("despues", p.getProduct().toString());
			}
		Log.d("despues Tobuy", findProduct(updatedProduct.getProductCode()).getProduct().toString());
	}

	@Override
	public void removeProduct(int code) {
		lista.remove(findProduct(code));
	}
	
	@Override
	public void remove(ArrayList<Integer> productPositions) {
		for(int i : productPositions)
			lista.remove(i);
	}

	@Override
	public ProductType findProduct(int productCode) {
		for(ProductType actualProduct : lista){
			if(actualProduct.getProduct().getCode() == productCode)
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
	
	@Override
	public int getCodeForANewProduct(){
		for(int i = 1; i < 1000; i++)
			if(findProduct(i) == null)
				return i;
		return -1;
	}

}