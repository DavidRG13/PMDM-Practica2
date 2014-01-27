package com.foc.RendererPattern;

import com.foc.model.Bought_Product;
import com.foc.model.Product;
import com.foc.model.ToBuy_Product;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class ProductRendererBuilder extends RendererBuilder<Product> {
	
	private OnClickListenerProvider provider;
	
	public ProductRendererBuilder() {}
	
	public ProductRendererBuilder(Context context, OnClickListenerProvider provider){
		super.context = context;
		this.provider = provider;
	}
	
	@Override
	public int getViewItemType(Product product) {
		return 0;
	}

	@Override
	public Renderer build(View view, Product product, int position) {
		if(product instanceof ToBuy_Product)
			return new ToBuy_ProductRenderer((Activity) getContext(), view, provider.getOnClickListener(position));
		if(product instanceof Bought_Product)
			return new Bought_ProductRenderer((Activity) getContext(), view, provider.getOnClickListener(position));
		if (product instanceof Product)
			return new ProductRenderer((Activity) getContext(), view, provider.getOnClickListener(position));
		return null;
	}

}