package com.foc.storage;

import android.content.ContentValues;
import android.content.Context;
import com.foc.model.General_Product;
import com.foc.model.Product;
import com.foc.model.ProductType;

public class ProductStorageDB extends StorageDB{
	
	private static final String TABLE = DbHelper.PRODUCT_TABLE;
	protected static final String ID = DbHelper.ID;
	
	public ProductStorageDB(Context context) {
		super(context);
	}

	@Override
	protected String getTable() {
		return TABLE;
	}

	@Override
	protected String getPK() {
		return ID;
	}

	@Override
	protected ProductType getProductTypeFrom(Product p) {
		return new General_Product(p);
	}
	
	@Override
	protected ContentValues getContentValuesFrom (Product p){
		return getCompleteContentValuesFrom(p);
	}

	@Override
	protected String getSelectAllQuery() {
		return SELECT_ALL + TABLE;
	}
	
	@Override
	protected void insert(Product p) {
		insert(DbHelper.PRODUCT_TABLE, getContentValuesFrom(p));
	}
	
}