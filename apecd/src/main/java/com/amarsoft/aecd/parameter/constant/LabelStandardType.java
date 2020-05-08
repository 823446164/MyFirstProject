package com.amarsoft.aecd.parameter.constant;

/**
 * 指标类型（RANK_STANDARD_ITEMS表）
 * @author yqchen1
 *
 */
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
