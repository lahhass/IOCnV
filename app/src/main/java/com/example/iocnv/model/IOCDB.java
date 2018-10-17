package com.example.iocnv.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.iocnv.db.IOCOpenHelper;
import com.example.iocnv.util.LogUtil;
import com.example.iocnv.util.MyApplication;

public class IOCDB {

	public static final String DB_NAME = "ioc";
	

	public static final int VERSION = 1;
	
	private static IOCDB iocDB;
	
	private SQLiteDatabase db;

	private IOCDB(Context context) {
		IOCOpenHelper dbHelper = new IOCOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	

	public synchronized static IOCDB getInstance(Context context) {
		if (iocDB == null) {
			iocDB = new IOCDB(context);
		}
		return iocDB;
	}

	public void saveAccount(Account account) {
		if (account != null) {
			ContentValues values = new ContentValues();
			values.put("name", account.getName());
			values.put("password", account.getPassword());
			db.insert("Account", null, values);
		}
	}
	

	public boolean validate(Account account, int flag) {
		Cursor cursor = db.
				rawQuery("select * from Account where name like ?", new String[]{account.getName()});

		if (cursor == null) {
			LogUtil.d("IOCDB", "cursor == null");
			return false;
		}
		

		switch (flag) {
		case 0:       //ע��
			if (cursor.getCount() == 0) {
				return true;
			} else {
				Toast.makeText(MyApplication.getContext(), "用户名已注册",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		case 1:       //��¼
			if (cursor.getCount() == 0 || cursor.getCount() > 1 ) {
				Toast.makeText(MyApplication.getContext(), "有问题",
						Toast.LENGTH_SHORT).show();
				return false;
			} else {

				if (cursor.moveToFirst()) {
					String passwordGet;
					passwordGet = cursor.getString(cursor.getColumnIndex("password"));
					if (passwordGet.equals(account.getPassword())) {
						return true;
					} else {
						Toast.makeText(MyApplication.getContext(), "用户名或密码错误",
								Toast.LENGTH_SHORT).show();
						return false;
					}
				}
			}
		default:
		}
		return false;
	}

}
