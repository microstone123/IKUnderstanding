package com.example.ikandroid;

import java.io.File;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;

public class DBHelper {
	
	private final static int DB_VERSION = 56;
	private final static String DB_NAME = "exploration.db";

	public static DbUtils createDb(Context context) {
		
		DbUtils db = null;
//		if (Constants.IS_TEST) {
			String dbPath = FileUtils.getRootPathById(context, "db");
			checkPath(dbPath);
			db = DbUtils.create(context, dbPath, DB_NAME, DB_VERSION, null);
//		} else {
//			db = DbUtils.create(context, DB_NAME, DB_VERSION, updateListener);
//		}
		return db;
	}

	public static boolean deleteDatabase(Context context, String name) {
		return context.deleteDatabase(name);
	}
	
	private static void checkPath(String dbPath) {
		File dir = new File(dbPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	private static DbUpgradeListener updateListener = new DbUpgradeListener() {

		@Override
		public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
			Log.i("", "dbVersion:" + oldVersion + " new:" + newVersion);
//			if (newVersion > oldVersion) {
//				try {
//					db.dropTable(ExplorationInfo.class);
//				} catch (DbException e) {
//				}
//			}

		}
	};
}
