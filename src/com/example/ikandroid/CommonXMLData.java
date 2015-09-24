package com.example.ikandroid;

import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;

public class CommonXMLData {
//	private final static String FILE_PATH_DIR_XML_INFO = "xml_info";

	public enum XmlFile {
		NOUNS_INFO("nouns_xml.xml");

		private String path;

		private XmlFile(String path) {
			this.setPath(path);
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
	}

	public static HashMap<String, NounsInfo> getNounsInfo(Context context, XmlFile xmlFile) {
		HashMap<String, NounsInfo> xmlInfos = new HashMap<String, NounsInfo>();
		InputStream in = null;
		try {
			in = context.getResources().getAssets().open(xmlFile.getPath());
			xmlInfos = XmlUtils.parseUserInfo(in);
			in.close();
		} catch (Exception e) {
			// do nothing
		}
		return xmlInfos;
	}
}
