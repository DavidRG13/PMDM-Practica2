package com.foc.storage;

import utilities.Messenger;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "store";
	private static final int DATABASE_VERSION = 4;
	
	//SQL commands
	private static final String CREATE_TABLE = "CREATE TABLE ";
	private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";
	private static final String FOREIGN_KEY = "FOREIGN KEY ";
	private static final String REFERENCES = "REFERENCES ";
	private static final String PK = "PRIMARY KEY ";
	private static final String AI = "AUTOINCREMENT ";
	
	//Table names
	static final String PRODUCT_TABLE = "products";
	static final String TOBUY_PRODUCT_TABLE = "tobuy";
	static final String BOUGHT_PRODUCT_TABLE = "bought";
	
	//Common column names
	static final String ID = "_id";
	static final String FK_ID = "productCode";
	
	//Productos table column names
	static final String NAME = "Name";
	static final String PRICE = "Price";
	static final String DESC = "Description";
	static final String IMG = "image";
	
	private static final String CREATE_TABLE_PRODUCT = CREATE_TABLE + PRODUCT_TABLE +" ("
			+ ID +" INTEGER "+ PK + AI +","
			+ NAME +" VARCHAR(50),"
			+ PRICE +" REAL,"
			+ DESC +" TEXT(255),"
			+ IMG +" TEXT(20)"
			+ ");";
	
	private static final String CREATE_TABLE_TOBUY = CREATE_TABLE + TOBUY_PRODUCT_TABLE +" ("
			+ ID +" INTEGER "+ PK + AI +","
			+ FK_ID +" INTEGER,"
			+ FOREIGN_KEY +"(" + FK_ID +")"+ REFERENCES + PRODUCT_TABLE +"("+ ID +")" 
			+ ");";
	
	private static final String CREATE_TABLE_BOUGHT = CREATE_TABLE + BOUGHT_PRODUCT_TABLE +" ("
			+ ID +" INTEGER "+ PK + AI +","
			+ FK_ID +" INTEGER,"
			+ FOREIGN_KEY +"(" + FK_ID +")"+ REFERENCES + PRODUCT_TABLE +"("+ ID +")" 
			+ ");";
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_TABLE_PRODUCT);
			Messenger.log("tabla "+ PRODUCT_TABLE +" creada");
			db.execSQL(CREATE_TABLE_TOBUY);
			Messenger.log("tabla "+ TOBUY_PRODUCT_TABLE +" creada");
			db.execSQL(CREATE_TABLE_BOUGHT);
			Messenger.log("tabla "+ BOUGHT_PRODUCT_TABLE +" creada");
		} catch (SQLException e) {
			//Message.message( context, ""+ e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_IF_EXISTS + PRODUCT_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + TOBUY_PRODUCT_TABLE);
        db.execSQL(DROP_TABLE_IF_EXISTS + BOUGHT_PRODUCT_TABLE);

        onCreate(db);
	}

}