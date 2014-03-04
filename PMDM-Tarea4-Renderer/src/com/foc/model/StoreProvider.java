package com.foc.model;

import android.content.Context;

import com.foc.storage.BoughtProductStorageDB;
import com.foc.storage.ProductStorageDB;
import com.foc.storage.ToBuyProductStorageDB;

public class StoreProvider {
	
//	The 3 following methos are used to store in an ArrayList
	
//	public static Store getProductStore(){
//		return ProductStore.getStore();
//	}
//	
//	public static Store getBoughtProductStore(){
//		return BoughtProductStore.getStore();
//	}
//	
//	public static Store getToBuyProductStore(){
//		return ToBuyProductStore.getStore();
//	}
	
	
//	The 3 following methos are used to store in a DB
	
	public static Store getProductStore(Context context){
		return new ProductStorageDB(context);
	}
	
	public static Store getBoughtProductStore(Context context){
		return new BoughtProductStorageDB(context);
	}
	
	public static Store getToBuyProductStore(Context context){
		return new ToBuyProductStorageDB(context);
	}

}