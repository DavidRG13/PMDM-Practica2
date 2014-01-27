package com.foc.RendererPattern;

import android.content.Context;
import android.view.View;

public abstract class RendererBuilder<T> {

	protected Context context;

	public RendererBuilder() {}

	public Context getContext() {
		return context;
	}

	public abstract Renderer<T> build(View view, T object, int position);
	
	public abstract int getViewItemType(T product);
	
	public int getViewTypeCount(){
		return 0;
	}

	public boolean isReciclable(View view) {
		return (view == null) ? false : true;
	}

}