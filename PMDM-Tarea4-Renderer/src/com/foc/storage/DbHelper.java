package com.foc.storage;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "";
	private static final int DATABASE_VERSION = 1; 
	private static final String TABLE_NAME = "";
	private static final String UID = "_id";
	private static final String NAME = "Name";
	private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +" ("
			+ UID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ NAME +" VARCHAR(50),"
			+ ");";
	private Context context;
	
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO completar creación tabla
		try {
			db.execSQL(CREATE_TABLE);
		} catch (SQLException e) {
			//Message.message( context, ""+ e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}