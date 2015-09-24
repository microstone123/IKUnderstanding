package com.example.ikandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@ContentView(R.layout.second_main)
public class SecondActivity extends FragmentActivity {

	@ViewInject(R.id.et1)
	private EditText etquestion;

	@ViewInject(R.id.tv1)
	private TextView tvanswer;

	@ViewInject(R.id.jiance_tv)
	private TextView jiance_tv;
	private String[] keyStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this); // 注入view和事件
	}

	@OnClick(R.id.btnone)
	public void fencwwwww(View v) {
		String s = IKAnalyzerHelper.getAnalyWord(etquestion.getText().toString());
		keyStr = s.split(";");
		tvanswer.append(s + "\n");
		etquestion.setText("");

	}

	@OnClick(R.id.btn3)
	public void jiancedsds(View v) {
		String str1 = "";
		for (int i = 0; i < keyStr.length; i++) {
			NounsInfo info = ((GuideApp) getApplication()).nounsHash.get(keyStr[i]);
			if (info != null)
				str1 = str1 + info.getName() + info.getModel() + "\n";
		}
		jiance_tv.setText(str1);

	}

}
