/*
 * 文件名：LabelStandardType.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：指标类型（RANK_STANDARD_ITEMS表）
 * 修改人：yqchen1
 * 修改时间：2020年5月8日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.parameter.constant;

public enum LabelStandardType {
	
	Basic("1","公共基础型"),
	Optional("2","可选加分型"),
	;
	
	public final String id;
	public final String name;
	
	private LabelStandardType(String id, String name) {
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
		return Basic.id.equals(id) || Optional.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (LabelStandardType rankType : LabelStandardType.values()) {
			if (rankType.id.equalsIgnoreCase(id)) {
				return rankType.name;
			}
		}
		return "";
	}

}
