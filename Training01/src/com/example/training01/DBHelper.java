package com.example.training01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "training01.db";
	private static final int DATABASE_VERSION = 5;
	private static final String SQL_TASK_TABLE = "CREATE TABLE IF NOT EXISTS TASKS (DATE VARCHAR, DESCRIPTION VARCHAR)";
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_TASK_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS TASKS");
		onCreate(db);
	}

}
