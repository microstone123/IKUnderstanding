package com.example.ikandroid;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
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
	private OrderGroup orderGroup = new OrderGroup();

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
		orderGroup = new OrderGroup();
	}

	@OnClick(R.id.btn3)
	public void jiancedsds(View v) {
		String str1 = "";
		jiance_tv.setText("");
		// for (int i = 0; i < keyStr.length; i++) {
		setOrderGroup(keyStr);
		if (orderGroup == null) {
			jiance_tv.setText(jiance_tv.getText() + "找不到信息" + "\n");
			return;
		}
		orderGroup.setGroup("(" + getEndsWith(orderGroup.getProductMark()) + ")("
				+ getEndsWith(orderGroup.getTypeMark()) + ")(" + getEndsWith(orderGroup.getTechnologyMark()) + ")");
		jiance_tv.setText(jiance_tv.getText() + orderGroup.getGroup() + "\n");
	}

	private String getEndsWith(String s) {
		if (s.endsWith(";")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	private List<NounsInfo> setOrderGroup(String[] keystr) {
		List<NounsInfo> nounsInfo = new ArrayList<NounsInfo>();
		for (int i = 0; i < keyStr.length; i++) {
			NounsInfo info = ((GuideApp) getApplication()).nounsHash.get(keyStr[i]);
			if (info != null)
				nounsInfo.add(info);
		}
		getOrderGroup(nounsInfo);
		return nounsInfo;
	}

	private void getOrderGroup(List<NounsInfo> info) {
		if (info.size() == 0) {
			orderGroup = null;
		} else {
			for (NounsInfo nounsInfo : info) {
				setOrderGroup(nounsInfo);
			}
		}
	}

	private void setOrderGroup(NounsInfo info) {
		if (info.getModel().contains(NounsType.PRODUCT_INFO)) {
			orderGroup.setProduct(orderGroup.getProduct() + info.getName());
			int num = orderGroup.getProductNum() + 1;
			orderGroup.setProductNum(num);
			orderGroup.setProductMark(removeSameMark(orderGroup.getProductMark(),info.getMark()));
			if (StringUtils.isNotEmpty(info.getRelated())) {
				orderGroup.setTypeMark(removeSameMark(orderGroup.getTypeMark(),info.getRelated()));
			}
		} else if (info.getModel().contains(NounsType.TECHNOLOGY_INFO)) {
			orderGroup.setTechnology(orderGroup.getTechnology() + info.getName());
			int num = orderGroup.getTechnologyNum() + 1;
			orderGroup.setTechnologyNum(num);
			orderGroup.setTechnologyMark(removeSameMark(orderGroup.getTechnologyMark(),info.getMark()));
		} else if (info.getModel().contains(NounsType.TYPE_INFO)) {
			orderGroup.setType(orderGroup.getType() + info.getName());
			int num = orderGroup.getTypeNum() + 1;
			orderGroup.setTypeNum(num);
			orderGroup.setTypeMark(removeSameMark(orderGroup.getTypeMark(),info.getMark()));
		}
	}

	private String removeSameMark(String order,String mark) {
		if (StringUtils.isEmpty(order)) {
			return mark + ";";
		}
		String[] typeMark = order.split(";");
		boolean isSame = false;
		for (int i = 0; i < typeMark.length; i++) {
			if (mark.equals(typeMark[i])) {
				typeMark[i] = mark;
				isSame = true;
				break;
			}
		}
		if (!isSame) {
			mark = order + mark + ";";
		} else {
			mark = order;
		}
		return mark;
	}

}
