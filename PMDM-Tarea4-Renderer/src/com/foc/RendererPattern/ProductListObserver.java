package com.foc.RendererPattern;

public interface ProductListObserver {
	
	public void onListItemClick(int productCode);
	
	public void actionItemClicked(int itemId);

}