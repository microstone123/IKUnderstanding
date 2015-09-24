package com.example.ikandroid;

import java.util.HashMap;

import android.app.Application;

import com.example.ikandroid.CommonXMLData.XmlFile;

public class GuideApp extends Application {

	public HashMap<String, NounsInfo> nounsHash = new HashMap<String, NounsInfo>();

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
				getNounsInfo();
			};
		}.start();

	}

	private void getNounsInfo() {
		nounsHash = CommonXMLData.getNounsInfo(this, XmlFile.NOUNS_INFO);
	}
}
