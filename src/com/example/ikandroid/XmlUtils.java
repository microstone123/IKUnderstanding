package com.example.ikandroid;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

public class XmlUtils {

	public static HashMap<String, NounsInfo> parseUserInfo(InputStream is) throws Exception {
		HashMap<String, NounsInfo> xmlInfos = null;
		NounsInfo xmlInfo = null;

		XmlPullParser parser = Xml.newPullParser(); // 由android.util.Xml创建一个XmlPullParser实例
		parser.setInput(is, "UTF-8"); // 设置输入流 并指明编码方式
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				xmlInfos = new HashMap<String, NounsInfo>();
				break;
			case XmlPullParser.START_TAG:
				if (parser.getName().equals("info")) {
					xmlInfo = new NounsInfo();
				}
				if (parser.getName().equals("name")) {
					eventType = parser.next();
					xmlInfo.setName(parser.getText());
				} else if (parser.getName().equals("model")) {
					eventType = parser.next();
					xmlInfo.setModel(parser.getText());
				}
				break;
			case XmlPullParser.END_TAG:
				if (parser.getName().equals("info")) {
					xmlInfos.put(xmlInfo.getName(), xmlInfo);
					xmlInfo = null;
				}
				break;
			}
			eventType = parser.next();
		}
		return xmlInfos;
	}

}
