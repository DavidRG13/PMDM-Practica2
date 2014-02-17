package com.foc.model;

import java.util.ArrayList;

public class BoughtProductStore implements Store{
	
	private static BoughtProductStore instancia = new BoughtProductStore();
	private ArrayList<ProductType> lista;
	
	public static BoughtProductStore getStore(){
		return instancia;
	}
	
	private BoughtProductStore(){
		lista = new ArrayList<ProductType>();
		lista.add(new Bought_Product(new Product(4,"producto 4", 12.3, "descripcion 4", "dulces")));
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
		lista.add(new Bought_Product(new Product(getCodeForANewProduct(), name, price, description, icon)));
	}

	@Override
	public void updateProduct(ProductType updatedProduct) {
		for(ProductType p : lista)
			if(p.getProductCode() == updatedProduct.getProductCode())
				p.setProduct(updatedProduct.getProduct());
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
