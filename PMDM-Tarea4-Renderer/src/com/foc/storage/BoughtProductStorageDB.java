package com.foc.storage;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;

import com.foc.model.Bought_Product;
import com.foc.model.Product;
import com.foc.model.ProductType;

public class BoughtProductStorageDB extends StorageDB {
	
	private static final String TABLE = DbHelper.BOUGHT_PRODUCT_TABLE;
	protected static final String ID = DbHelper.ID;
	
	public BoughtProductStorageDB(Context context) {
		super(context);
	}

	@Override
	protected ProductType getProductTypeFrom(Product p) {
		return new Bought_Product(p);
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
		if(p.getCode() == 0){
			int position = (int) insert(DbHelper.PRODUCT_TABLE, getCompleteContentValuesFrom(p));
			ArrayList<ProductType> readAll = readAllFrom(DbHelper.PRODUCT_TABLE);
			ProductType product = readAll.get(position - 1);
			p = product.getProduct();
		}else{
			removeFrom(DbHelper.TOBUY_PRODUCT_TABLE, p.getCode());
		}
		insert(TABLE, getContentValuesFrom(p));
	}

}