package com.foc.model;

import java.io.Serializable;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class ProductType implements Serializable{

	private static final long serialVersionUID = -2422724463887284241L;
	private View view;
	private Activity context;
	private Product product;
	private OnClickListener listener;
	
	public ProductType() {}
	
	public ProductType(Activity context, View view, Product product, OnClickListener listener){
		this.context = context;
		this.view = view;
		this.product = product;
		this.listener = listener;
	}
	
	public ProductType(Product product) {
		this.product = product;		
	}

	public View getView() {
		return view;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Activity getContext() {
		return context;
	}
	
	public void setContext(Activity context) {
		this.context = context;
	}
	
	public OnClickListener getListener() {
		return listener;
	}
	
	public void setListener(OnClickListener listener) {
		this.listener = listener;
	}
	
	public LayoutInflater getLayoutInflater() {
		return context.getLayoutInflater();
	}
	
	public View render(){
		if (view == null) inflate();
		return renderProduct();
	}
	
	public abstract void inflate();
	
	public abstract View renderProduct();

	public void buildView(int viewResource) {
		Log.d("AQUIII", "build view");
		view = getLayoutInflater().inflate(viewResource, null);
	}
	
	public abstract Store getStore();

	public void setProduct(String name, double price, String description, String icon) {
//		if(store == null)
//			Log.d("AQUIII", "store es nulo!!!!!!!");
//		int code = store.getCodeForANewProduct();
//		setProduct(new Product(code, name, price, description, icon));
	}
	
}