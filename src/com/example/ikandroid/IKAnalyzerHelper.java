package com.example.ikandroid;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class IKAnalyzerHelper {
	
	public static final String IKANALY_SEPARATOR = ";";

	public static String getAnalyWord(String word) {
		String s = "";
		String [] strArray = new String [20];
		int i=0;
		try {
			StringReader re = new StringReader(word);
			IKSegmenter ik = new IKSegmenter(re, true);
			Lexeme lex = null;
			while ((lex = ik.next()) != null) {
				s = s + lex.getLexemeText() + IKANALY_SEPARATOR;
				strArray[i++]=lex.getLexemeText();
//				Log.e("lex", ""+strArray[i-1]);
			}
			
			WordUnderstander(strArray,i);
			
			if (s.endsWith(IKANALY_SEPARATOR)) {
				s = s.substring(0, s.length() - 1);
			}
			
			re.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			s = null;
		}
		return s;
	}
	public static void WordUnderstander(String [] Sentence, int length){
	
		int j=0;
		int k=0;
		
		for(int i=0;i<length;i++){
			
//			Log.e("lex", "WordUnderstander:"+Sentence[i]);
			
			if(MainActivity.KeyWords.contains(Sentence[i])){
				
				MainActivity.KeywordList[j++]=Sentence[i];
				Log.e("lex", "WordUnderstander:"+Sentence[i]);
				
			}
			if(MainActivity.KeyVerbs.contains(Sentence[i])){

				MainActivity.KeyverbList[k++]=Sentence[i];
				Log.e("lex", "WordUnderstander:"+Sentence[i]);

			}
			
		}
		
//		FindInfo(KeywordList);
		
	
	}

}
