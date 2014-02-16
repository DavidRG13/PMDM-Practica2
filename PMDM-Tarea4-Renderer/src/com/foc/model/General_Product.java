package com.foc.model;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.foc.tarea4.R;

public class General_Product extends ProductType {
	
	private static final long serialVersionUID = 4438038195286614625L;

	public General_Product (Activity context, View view, Product product, OnClickListener listener){
		super(context, view, product, listener);
	}

	public General_Product(Product product) {
		super(product);
	}
	
	public General_Product() {}

	@Override
	public void inflate() {
		buildView(R.layout.list_product_view);
	}

	@Override
	public View renderProduct() {
		render(getProduct());
		setListeners();
		return getView();
	}
	
	private void setListeners(){
		addIconClickListener();
	}
	
	private void render(Product product){
		renderIcon(product.getImage());
		renderTitle(product.getName());
		renderDescription(product.getDescription());
		renderPrice(product.getPrice());
	}
	
	public void renderIcon(String image){
		int img = getContext().getResources().getIdentifier(image, "drawable", getContext().getPackageName());
		ImageView icon = (ImageView) getView().findViewById(R.id.product_icon);
		icon.setBackgroundResource(img);
	}
	
	public void addIconClickListener(){
		ImageView icon = (ImageView) getView().findViewById(R.id.product_icon);
		icon.setOnClickListener(getListener());
	}
	
	public void renderTitle(String name){
		TextView nameTextView = (TextView) getView().findViewById(R.id.product_name);
		nameTextView.setText(name);
	}
	
	public void renderDescription(String description){
		TextView descriptionTextView = (TextView) getView().findViewById(R.id.product_description);
		descriptionTextView.setText(description);
	}
	
	public void renderPrice(double price){
		TextView priceTextView = (TextView) getView().findViewById(R.id.product_price);
		priceTextView.setText(String.valueOf(price) + " € ");
	}
	
	public void setProduct(String name, double price, String description, String icon) {
		int code = ProductStore.getStore().getCodeForANewProduct();
		setProduct(new Product(code, name, price, description, icon));
	}

	@Override
	public Store getStore() {
		return ProductStore.getStore();
	}

}
