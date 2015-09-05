package com.example.ikandroid;

import java.io.IOException;
import java.io.StringReader;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private EditText etquestion = null;
	private TextView tvanswer = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etquestion = (EditText) super.findViewById(R.id.editText1);
		tvanswer = (TextView) super.findViewById(R.id.textView1);
	}

	public void fenci(View view) throws IOException {
		String s = "";
		// 独立Lucene实现
		StringReader re = new StringReader(etquestion.getText().toString());
		IKSegmenter ik = new IKSegmenter(re, true);
		Lexeme lex = null;
		while ((lex = ik.next()) != null) {
			s = s + lex.getLexemeText() + "|";
		}
		tvanswer.append(s + "\n");
		etquestion.setText("");
	}

	public void setExt(View view) throws IOException {
		FileUtils.writeProperties(this, "ext.dic");
		tvanswer.setText(FileUtils.getReadFile(this, "ext.dic"));
	}

}
