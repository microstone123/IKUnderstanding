package com.example.ikandroid;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.util.Log;

public class FileUtils {

	public static String getReadFile(Context context, String fileName) {
		String res = "";
		try {
			// 得到资源中的Raw数据流
			InputStream in = context.getClass().getClassLoader().getResourceAsStream("ext.dic");
			// 得到数据的大小
			int length = in.available();
			byte[] buffer = new byte[length];
			// 读取数据
			in.read(buffer);
			// 依test.txt的编码类型选择合适的编码，如果不调整会乱码
			res = EncodingUtils.getString(buffer, "UTF-8");
			// 关闭
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	// 写数据
	public static boolean writeFile(Context context, String fileName, String writestr) throws IOException {
		boolean isWrite = false;
		try {
			FileOutputStream fout = context.openFileOutput("", context.MODE_PRIVATE);
			byte[] bytes = writestr.getBytes();
			fout.write(bytes);
			fout.close();
			isWrite = true;
		} catch (Exception e) {
			e.printStackTrace();
			isWrite = false;
		}
		return isWrite;
	}

	public static void writeProperties(Context context, String parameterName) {
		Properties prop = new Properties();
		try {
			prop.load(context.getClass().getClassLoader().getResourceAsStream(parameterName));
			OutputStream fos = new FileOutputStream(context.getClass().getClassLoader().getResource(parameterName).getPath());
			prop.store(fos, parameterName + "\n");
			fos.close();
		} catch (IOException e) {
			Log.e("IOException", "Visit " + " for updating " + parameterName + " value error;"+e.getMessage());
		}
	}
}
