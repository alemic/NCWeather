package com.mlxy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** 数据库管理类。处理常用城市数据的存取。*/
public class DatabaseManager extends SQLiteOpenHelper {
	private static final String DB_NAME = "db_weather";
	private static final int DB_VERSION = 1;
	
	/** 常用城市的<b>表</b>名。*/
	public static final String TABLE_NAME_FAVORITE = "table_favorite";
	/** 常用城市的<b>id</b>字段名。*/
	public static final String FIELD_ID_FAVORITE = "id";
	/** 常用城市的<b>城市名</b>字段名。*/
	public static final String FIELD_CITY_FAVORITE = "city";

	public DatabaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 创建一个有ID和城市名两个字段的表。
		String sql = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
						TABLE_NAME_FAVORITE, FIELD_ID_FAVORITE, FIELD_CITY_FAVORITE);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 待补充。
	}
	
	/** 调取表中所有字段的所有信息。</br>
	 * 相当于<b>SELECT * FROM TABLE_NAME</b>。
	 * 
	 * @return 包含所有信息的Cursor。*/
	public Cursor selectAll() {
		SQLiteDatabase db = this.getReadableDatabase();
		
		// 调取所有信息。
		Cursor cursorToReturn = db.query(TABLE_NAME_FAVORITE, null, null, null, null, null, null);
		return cursorToReturn;
	}
	
	/** 向表中插入一条信息。
	 * 
	 * @param content 需要插入的信息。*/
	public void insert(String content) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FIELD_CITY_FAVORITE, content);
		
		db.insert(TABLE_NAME_FAVORITE, null, values);
	}
	
	/** 删除包含内容的行。
	 * 
	 * @param content 要删除的行所包含的内容。*/
	public void delete(String content) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 条件语句。问号作通配符用，匹配whereArgs中的值。
		String whereClause = FIELD_CITY_FAVORITE + " = ?";
		// 条件变量。
		String[] whereArgs = {content};
		
		db.delete(TABLE_NAME_FAVORITE, whereClause, whereArgs);
	}
	
	/** 更新内容。
	 * 
	 * @param oldContent 需要被更新的内容。
	 * @param newContent 新内容。*/
	public void update(String oldContent, String newContent) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		// 要更新的内容。
		ContentValues values = new ContentValues();
		values.put(FIELD_CITY_FAVORITE, newContent);
		
		String whereClause = FIELD_CITY_FAVORITE + " = ?";
		String[] whereArgs = {oldContent};
		
		db.update(TABLE_NAME_FAVORITE, values, whereClause, whereArgs);
	}
}
