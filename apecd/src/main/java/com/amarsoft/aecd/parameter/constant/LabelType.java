/*
 * 文件名：LabelType.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：指标类型（LABEL_CATALOG表）
 * 修改人：yqchen1
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.parameter.constant;

public enum LabelType {
	
	_1("1","目录"),
	_2("2","指标"),
	_3("3","标签"),
	_4("4","其他"),
	;
	
	public final String id;
	public final String name;
	
	private LabelType(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isExist(String id) {
		return _1.id.equals(id) || _2.id.equals(id) || _3.id.equals(id) || _4.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (LabelType labelType : LabelType.values()) {
			if (labelType.id.equalsIgnoreCase(id)) {
				return labelType.name;
			}
		}
		return "";
	}

}
