package com.example.ikandroid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileUtils {

	public static String getSDCardRootPath(String dirName) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + dirName;
			
		} else {
			return null;
		}
	}
	public static String getRootPathById(Context context, String dirName) {
		String rootPath = null;
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

			rootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
					+ context.getPackageName() + File.separator + dirName + File.separator;
		} else {
			rootPath = context.getCacheDir().getAbsolutePath() + File.separator + dirName + File.separator;
		}
		File dir = new File(rootPath);
		dir.mkdirs();
		return rootPath;
	}

	public static String getReadFile(Context context, String fileName) {
		String res = "";
		try {
			InputStream in = context.getClass().getClassLoader().getResourceAsStream(fileName);
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			res = EncodingUtils.getString(buffer, "UTF-8");
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

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
