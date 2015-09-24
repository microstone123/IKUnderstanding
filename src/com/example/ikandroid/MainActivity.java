package com.example.ikandroid;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.example.ikandroid.DBHelper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private EditText etquestion = null;
	private TextView tvanswer = null;
	static String KeyWords="";
	static String KeyVerbs="";
	static public DbUtils dbUtils;
	private DBHelper dBHelper;
	static String [] KeywordList = new String [10];
	static String [] KeyverbList = new String [10];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etquestion = (EditText) super.findViewById(R.id.editText1);
		findViewById(R.id.button3).setOnClickListener(this);;
		tvanswer = (TextView) super.findViewById(R.id.textView1);
		
//		Log.e("File Path", "File Path:"+FileUtils.getSDCardRootPath("com.example.ikandroid"));
		
//		FileUtils.writeProperties(this, "keywords.dic");
		KeyWords=FileUtils.getReadFile(getApplicationContext(), "keywords.dic");
		Log.e("KeyWords", "KeyWords:"+KeyWords);
		
//		FileUtils.writeProperties(this, "keyverbs.dic");
		KeyVerbs=FileUtils.getReadFile(getApplicationContext(), "keyverbs.dic");		
		Log.e("KeyVerbs", "KeyVerbs:"+KeyVerbs);
		
		if(DBHelper.deleteDatabase(this,"exploration.db"))
			Log.e("DB Delete", "DB Delete:");
		
		
		dbUtils = DBHelper.createDb(this);
		
		try {

			dbUtils.dropDb();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executeAssetsSQL();
		
		
	}
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.button3: 
			FindInfo(KeywordList);
			FindAction(KeyverbList);
			
			break;
		default:
			break;
		}
		
	}
	public void fenci(View view) throws IOException {
		
		tvanswer.append(IKAnalyzerHelper.getAnalyWord(etquestion.getText().toString()) + "\n");
		
		etquestion.setText("");
		
	}
	
	public void setExt(View view) throws IOException {
		
		FileUtils.writeProperties(this, "ext.dic");
		tvanswer.setText(FileUtils.getReadFile(this, "ext.dic"));
	}
	public void FindAction(String [] Verbs){
		
		if(Verbs[0].compareTo("介绍")==0){
			
		}
		
		
	}
	
	
	public void FindInfo(String [] Nouns){
		
		Cursor cursor = null;
		String queryString="select * from test1";
		Log.e("findInfo","findInfo Enter");
//		for(int k=0;k<Nouns.length;k++){
			
			if(Nouns[0].compareTo("空调")==0){
				queryString.replace("*", "AirConditioner");
			}
			
			try{
				 cursor = dbUtils.execQuery(queryString);
				 Log.e("execQuery","execQuery Enter");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
				while (cursor.moveToNext()){
					String productname = cursor.getString(0); //获取第一列的值,第一列的索引从0开始
	//				int productid = cursor.getInt(1);
					Log.e("execQuery", "execQuery:"+productname);
	//				Log.e("execQuery", "execQuery:"+productid);
				}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//		}
			
		cursor.close();

	}
		
	private void executeAssetsSQL() {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(this.getAssets().open(
					Configuration.DB_PATH + "/" + Configuration.DB_NAME)));

			System.out.println("路径:" + Configuration.DB_PATH + "/" + Configuration.DB_NAME);
			String line;
			String buffer = "";

//			dbUtils.execNonQuery("update sqlite_sequence SET seq = 0 where name ='test1'");//自增长ID为0
			
			while ((line = in.readLine()) != null) {
				buffer += line;
				if (line.trim().endsWith(";")){
					dbUtils.execNonQuery(buffer.replace(";", ""));
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
