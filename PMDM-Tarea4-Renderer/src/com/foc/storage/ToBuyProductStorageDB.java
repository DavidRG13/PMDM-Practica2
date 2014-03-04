package com.foc.storage;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;

import com.foc.model.Product;
import com.foc.model.ProductType;
import com.foc.model.ToBuy_Product;

public class ToBuyProductStorageDB extends StorageDB {
	
	private static final String TABLE = DbHelper.TOBUY_PRODUCT_TABLE;
	protected static final String ID = DbHelper.ID;

	public ToBuyProductStorageDB(Context context) {
		super(context);
	}

	@Override
	protected ProductType getProductTypeFrom(Product p) {
		return new ToBuy_Product(p);
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
	protected ContentValues getContentValuesFrom (Product p){
		ContentValues cv = new ContentValues();
		cv.put(ID_FK, p.getCode());
		return cv;
	}

	@Override
	protected String getSelectAllQuery() {
		return SELECT_ALL + DbHelper.PRODUCT_TABLE + " WHERE "+ ID +" IN (SELECT "+ DbHelper.FK_ID +" FROM "+ TABLE +")";
	}

	@Override
	protected void insert(Product p) {
		int position = (int) insert(DbHelper.PRODUCT_TABLE, getCompleteContentValuesFrom(p));
		ArrayList<ProductType> readAll = readAllFrom(DbHelper.PRODUCT_TABLE);
		ProductType product = readAll.get(position - 1);
		insert(TABLE, getContentValuesFrom(product.getProduct()));
	}

}