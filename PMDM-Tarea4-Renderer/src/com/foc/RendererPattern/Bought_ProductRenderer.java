package com.foc.RendererPattern;

import com.foc.model.Bought_Product;
import com.foc.tarea4.R;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Bought_ProductRenderer extends Renderer<Bought_Product> {
	
	private OnClickListener listener;

	public Bought_ProductRenderer(Activity context, View view, OnClickListener listener) {
		super(context, view);
		this.listener = listener;
	}

	@Override
	public void inflate() {
		setView(getLayoutInflater().inflate(R.layout.list_product_view, null));
	}

	@Override
	public View renderObject(Bought_Product product) {
		renderProduct(product);
		setListeners();
		return getView();
	}
	
	private void setListeners(){
		addIconClickListener();
	}
	
	private void renderProduct(Bought_Product product){
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
		icon.setOnClickListener(listener);
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
		priceTextView.setText(String.valueOf(price) + " � ");
	}

}