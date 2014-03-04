package com.foc.model;

import java.util.ArrayList;

public abstract class StoreList implements Store {
	
	protected ArrayList<ProductType> lista;

	@Override
	public ArrayList<ProductType> getList() {
		return lista;
	}

	@Override
	public ArrayList<ProductType> getPositions(ArrayList<Integer> positions) {
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		for(int i : positions)
			list.add(lista.get(i));
		return list;
	}

	@Override
	public void addProduct(ProductType... products) {
		for(ProductType p : products)
			addProduct(p.getProductName(), p.getProductPrice(), p.getProductDescription(), p.getProductImage());
	}

	@Override
	public void addProduct(ArrayList<ProductType> products) {
		for(ProductType p : products)
			addProduct(p);
	}

	@Override
	public abstract void addProduct(String name, double price, String description, String icon);

	@Override
	public void updateProduct(ProductType updatedProduct) {
		for(ProductType p : lista)
			if(p.getProductCode() == updatedProduct.getProductCode())
				p.setProduct(updatedProduct.getProduct());
	}

	@Override
	public void remove(int code) {
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
	public int getCategoryIndex(String category) {
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

	public int getCodeForANewProduct() {
		for(int i = 1; i < 1000; i++)
			if(findProduct(i) == null)
				return i;
		return -1;
	}

}
