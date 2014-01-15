package com.example.training01;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TaskDAO {
	
	public void saveData(Context context, String date, String description) {
		DBHelper db = new DBHelper(context);
		SQLiteDatabase database = db.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("DATE", date);
		cv.put("DESCRIPTION", description);
		database.insert("TASKS", null, cv);
		db.close();
	}
	
	public List<String> listAll(Context context) {
		DBHelper db = new DBHelper(context);
		SQLiteDatabase database = db.getReadableDatabase();
		Cursor cursor = database.query("TASKS", new String[]{"DATE", "DESCRIPTION"}, null , null, null, null, null);
		cursor.moveToFirst();
		List<String> list = new ArrayList<String>();
		while (!cursor.isAfterLast()) {
			list.add(cursor.getString(0) + "," + cursor.getString(1));
			cursor.moveToNext();
		}
		db.close();
		return list;
	}

}
