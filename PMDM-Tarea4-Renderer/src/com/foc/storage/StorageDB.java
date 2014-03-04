package com.foc.storage;

import java.util.ArrayList;

import utilities.Messenger;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.foc.model.Product;
import com.foc.model.ProductType;
import com.foc.model.Store;

public abstract class StorageDB implements Store{

	protected static final String SELECT_ALL = "SELECT * FROM ";
	protected SQLiteDatabase db;
	private Context context;
	
	protected static final String ID = DbHelper.ID;
	protected static final String ID_FK = DbHelper.FK_ID;
	protected static final String IMG = DbHelper.IMG;
	protected static final String DESC = DbHelper.DESC;
	protected static final String PRICE = DbHelper.PRICE;
	protected static final String NAME = DbHelper.NAME;

	public StorageDB(Context context) {
		this.context = context;
	}
	
	protected Product getProductFrom(Cursor c) {
		Product p = new Product();
		p.setCode(c.getInt(c.getColumnIndex(ID)));
		p.setName(c.getString(c.getColumnIndex(NAME)));
		p.setPrice(c.getDouble(c.getColumnIndex(PRICE)));
		p.setDescription(c.getString(c.getColumnIndex(DESC)));
		p.setImage(c.getString(c.getColumnIndex(IMG)));
		return p;
	}
	
	protected ContentValues getContentValuesFrom (ProductType productType){
		Product p = productType.getProduct();
		return getContentValuesFrom(p);
	}
	
	private SQLiteDatabase getWritableDB() {
		return new DbHelper(context).getWritableDatabase();
	}
	
	@Override
	public ArrayList<ProductType> getList() {
		return readAll();
	}
	
	public ArrayList<ProductType> readAll() {
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		db = getWritableDB();
		Cursor c = db.rawQuery(getSelectAllQuery(), null);
		if(c.moveToFirst()){
			do{
				list.add(getProductTypeFrom(getProductFrom(c)));
			}while(c.moveToNext());
		}
		db.close();
		return list;
	}
	
	public ArrayList<ProductType> readAllFrom(String table) {
		ArrayList<ProductType> list = new ArrayList<ProductType>();
		db = getWritableDB();
		Cursor c = db.rawQuery(SELECT_ALL + table, null);
		if(c.moveToFirst()){
			do{
				list.add(getProductTypeFrom(getProductFrom(c)));
			}while(c.moveToNext());
		}
		db.close();
		return list;
	}

	@Override
	public ArrayList<ProductType> getPositions(ArrayList<Integer> positions) {
		ArrayList<ProductType> listaResultado = new ArrayList<ProductType>();
		ArrayList<ProductType> listaTodos = readAll();
		for(int position : positions)
			listaResultado.add(listaTodos.get(position));		
		return listaResultado;
	}

	@Override
	public void addProduct(ProductType... products) {
		for(ProductType product : products){
			Product p = product.getProduct();
			insert(p);
		}
	}

	@Override
	public void addProduct(ArrayList<ProductType> products) {
		for(ProductType p : products)
			addProduct(p);
	}

	@Override
	public void addProduct(String name, double price, String description, String icon) {
		Product p = new Product(0, name, price, description, icon);
		insert(p);
	}

	public long insert (String table, ContentValues cv){
		db = getWritableDB();
		long result = db.insert(table, null, cv);
		Messenger.log("Añadido  productos en la posicion "+ result +" de la tabla "+ table);
		db.close();
		return result;
	}

	@Override
	public void updateProduct(ProductType updatedProduct) {
		int code = updatedProduct.getProductCode();
		db = getWritableDB();
		ContentValues productContentValue = getCompleteContentValuesFrom(updatedProduct.getProduct());
		db.update(DbHelper.PRODUCT_TABLE, productContentValue, ID +"= ? ", new String[] {String.valueOf(code)});
		db.close();
	}

	@Override
	public void remove(int code) {
		db = getWritableDB();
		db.delete(getTable(), getPK() +" = ?", new String[]{String.valueOf(code)});
		db.close();
	}
	
	public void removeFrom(String table, int code) {
		db = getWritableDB();
		db.delete(table, getPK() +" = ?", new String[]{String.valueOf(code)});
		db.close();
	}

	@Override
	public void remove(ArrayList<Integer> productPositions) {
		ArrayList<ProductType> lista = getPositions(productPositions);
		for(ProductType p : lista)
			remove(p.getProductCode());		
	}

	@Override
	public ProductType findProduct(int productCode) {
		ProductType p = null;
		String query = SELECT_ALL + DbHelper.PRODUCT_TABLE +" WHERE "+ getPK() +" = "+ productCode;
		db = getWritableDB();
		Cursor c = db.rawQuery(query, null);
		if(c.moveToFirst())
			p = getProductTypeFrom(getProductFrom(c));
		db.close();
		return p;
	}
	
	protected ContentValues getCompleteContentValuesFrom (Product p){
		ContentValues cv = new ContentValues();
		cv.put(NAME, p.getName());
		cv.put(PRICE, p.getPrice());
		cv.put(DESC, p.getDescription());
		cv.put(IMG, p.getImage());
		return cv;
	}

	@Override
	public int getCategoryIndex(String category) {
		if(category.equals("comida"))
			return 0;
		if(category.equals("bebida"))
			return 1;
		if(category.equals("limpieza"))
			return 2;
		if(category.equals("dulces"))
			return 3;
		return -1;
	}
	
	protected abstract ProductType getProductTypeFrom (Product p);
	
	protected abstract String getTable();
	
	protected abstract String getPK();
	
	protected abstract String getSelectAllQuery();
	
	protected abstract void insert(Product p);
	
	protected abstract ContentValues getContentValuesFrom (Product p);

}