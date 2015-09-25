package com.example.ikandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NounsTypeHelper {
	public HashMap<String, NounsInfo> nounsHash = new HashMap<String, NounsInfo>();

	public NounsTypeHelper(HashMap<String, NounsInfo> nounsHash) {
		this.nounsHash = nounsHash;
	}

	public OrderGroup getOrderGroup(String[] keyStr) {
		OrderGroup orderGroup = new OrderGroup();
		List<NounsInfo> nounsInfo = setOrderGroup(keyStr);
		orderGroup = getOrderGroup(nounsInfo);
		return orderGroup;
	}

	private String getEndsWith(String s) {
		if (s.endsWith(";")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	private List<NounsInfo> setOrderGroup(String[] keystr) {
		List<NounsInfo> nounsInfo = new ArrayList<NounsInfo>();
		for (int i = 0; i < keystr.length; i++) {
			NounsInfo info = nounsHash.get(keystr[i]);
			if (info != null)
				nounsInfo.add(info);
		}
		return nounsInfo;
	}

	private OrderGroup getOrderGroup(List<NounsInfo> info) {
		OrderGroup orderGroup = new OrderGroup();
		if (info.size() == 0) {
			return null;
		} else {
			for (NounsInfo nounsInfo : info) {
				setOrderGroup(nounsInfo, orderGroup);
			}
		}
		orderGroup.setGroup("(" + getEndsWith(orderGroup.getProductMark()) + ")("
				+ getEndsWith(orderGroup.getTypeMark()) + ")(" + getEndsWith(orderGroup.getTechnologyMark()) + ")");
		return orderGroup;
	}

	private void setOrderGroup(NounsInfo info, OrderGroup orderGroup) {
		if (info.getModel().contains(NounsType.PRODUCT_INFO)) {
			orderGroup.setProduct(orderGroup.getProduct() + info.getName());
			int num = orderGroup.getProductNum() + 1;
			orderGroup.setProductNum(num);
			orderGroup.setProductMark(removeSameMark(orderGroup.getProductMark(), info.getMark()));
			if (StringUtils.isNotEmpty(info.getRelated())) {
				orderGroup.setTypeMark(removeSameMark(orderGroup.getTypeMark(), info.getRelated()));
			}
		} else if (info.getModel().contains(NounsType.TECHNOLOGY_INFO)) {
			orderGroup.setTechnology(orderGroup.getTechnology() + info.getName());
			int num = orderGroup.getTechnologyNum() + 1;
			orderGroup.setTechnologyNum(num);
			orderGroup.setTechnologyMark(removeSameMark(orderGroup.getTechnologyMark(), info.getMark()));
		} else if (info.getModel().contains(NounsType.TYPE_INFO)) {
			orderGroup.setType(orderGroup.getType() + info.getName());
			int num = orderGroup.getTypeNum() + 1;
			orderGroup.setTypeNum(num);
			orderGroup.setTypeMark(removeSameMark(orderGroup.getTypeMark(), info.getMark()));
		}
	}

	private String removeSameMark(String order, String mark) {
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
