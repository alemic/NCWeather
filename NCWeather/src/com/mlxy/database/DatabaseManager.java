package com.mlxy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** ���ݿ�����ࡣ�����ó������ݵĴ�ȡ��*/
public class DatabaseManager extends SQLiteOpenHelper {
	private static final String DB_NAME = "db_weather";
	private static final int DB_VERSION = 1;
	
	/** ���ó��е�<b>��</b>����*/
	public static final String TABLE_NAME_FAVORITE = "table_favorite";
	/** ���ó��е�<b>id</b>�ֶ�����*/
	public static final String FIELD_ID_FAVORITE = "id";
	/** ���ó��е�<b>������</b>�ֶ�����*/
	public static final String FIELD_CITY_FAVORITE = "city";

	public DatabaseManager(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// ����һ����ID�ͳ����������ֶεı�
		String sql = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)",
						TABLE_NAME_FAVORITE, FIELD_ID_FAVORITE, FIELD_CITY_FAVORITE);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// �����䡣
	}
	
	/** ��ȡ���������ֶε�������Ϣ��</br>
	 * �൱��<b>SELECT * FROM TABLE_NAME</b>��
	 * 
	 * @return ����������Ϣ��Cursor��*/
	public Cursor selectAll() {
		SQLiteDatabase db = this.getReadableDatabase();
		
		// ��ȡ������Ϣ��
		Cursor cursorToReturn = db.query(TABLE_NAME_FAVORITE, null, null, null, null, null, null);
		return cursorToReturn;
	}
	
	/** ����в���һ����Ϣ��
	 * 
	 * @param content ��Ҫ�������Ϣ��*/
	public void insert(String content) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FIELD_CITY_FAVORITE, content);
		
		db.insert(TABLE_NAME_FAVORITE, null, values);
	}
	
	/** ɾ���������ݵ��С�
	 * 
	 * @param content Ҫɾ�����������������ݡ�*/
	public void delete(String content) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		// ������䡣�ʺ���ͨ����ã�ƥ��whereArgs�е�ֵ��
		String whereClause = FIELD_CITY_FAVORITE + " = ?";
		// ����������
		String[] whereArgs = {content};
		
		db.delete(TABLE_NAME_FAVORITE, whereClause, whereArgs);
	}
	
	/** �������ݡ�
	 * 
	 * @param oldContent ��Ҫ�����µ����ݡ�
	 * @param newContent �����ݡ�*/
	public void update(String oldContent, String newContent) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		// Ҫ���µ����ݡ�
		ContentValues values = new ContentValues();
		values.put(FIELD_CITY_FAVORITE, newContent);
		
		String whereClause = FIELD_CITY_FAVORITE + " = ?";
		String[] whereArgs = {oldContent};
		
		db.update(TABLE_NAME_FAVORITE, values, whereClause, whereArgs);
	}
}
