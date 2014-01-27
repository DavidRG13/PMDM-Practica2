package com.foc.RendererPattern;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

public abstract class Renderer<T> {

	private View view;
	private Activity context;
	
	public Renderer(Activity context, View view){
		this.context = context;
		this.view = view;
	}
	
	public View getView() {
		return view;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public Activity getContext() {
		return context;
	}
	
	public LayoutInflater getLayoutInflater() {
		return context.getLayoutInflater();
	}
	
	public View render(T objet){
		if (view == null) inflate();
		return renderObject(objet);
	}
	
	public abstract void inflate();
	
	public abstract View renderObject(T objeto);
	
}