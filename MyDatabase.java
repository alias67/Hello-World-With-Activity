package com.example.database;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;


public class MyDatabase extends SQLiteAssetHelper{
	private static final String DATABASE_NAME = "Contact.db";
	private static final int DATABASE_VERSION = 1;

	public MyDatabase (Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public Cursor getPersons() {

		SQLiteDatabase db = getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

		String [] sqlSelect = {"0 _id", "Name"}; 
		String sqlTables = "Person";

		qb.setTables(sqlTables);
		Cursor c = qb.query(db, sqlSelect, null, null,
				null, null, null);

		c.moveToFirst();
		return c;

	}
}
