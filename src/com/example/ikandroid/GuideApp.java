package com.example.ikandroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.app.Application;
import android.util.Log;

public class GuideApp extends Application {

	public HashMap<String, String[]> nounsHash = new HashMap<String, String[]>();

	@Override
	public void onCreate() {
		super.onCreate();
		new Thread() {
			public void run() {
				IKAnalyzerHelper.getAnalyWord("hello world");
			};
		}.start();
		new Thread() {
			public void run() {
				executeAssetsSQL();
			};
		}.start();

	}

	private void setHashMap(String value) {
		String[] str = value.split(":");
		nounsHash.put(str[0], str);
	}

	/**
	 * 读取数据库文件（.sql），并执行sql语句
	 * */
	private void executeAssetsSQL() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.getAssets().open(
					Configuration.DB_PATH + "/" + "Nouns.txt")));

			System.out.println("路径:" + Configuration.DB_PATH + "/" + "Nouns.txt");
			String line;
			String buffer = "";
			while ((line = in.readLine()) != null) {
				buffer += line;
				if (line.trim().endsWith(";")) {
					setHashMap(buffer.replace(";", ""));
					buffer = "";
				}
			}
		} catch (Exception e) {
			Log.e("db-error", e.toString());
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				Log.e("db-error", e.toString());
			}
		}
	}
}
