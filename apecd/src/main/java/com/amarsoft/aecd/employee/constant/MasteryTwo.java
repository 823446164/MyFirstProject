/*
 * 文件名：MasteryTwo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：指标类型（RANK_STANDARD_ITEMS表）-掌握程度二类:熟练/精通
 * 修改人：yqchen1
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.employee.constant;

public enum MasteryTwo {
	
	_1("1","了解"),
	_2("2","熟悉"),
	_3("3","精通"),
	;
	
	public final String id;
	public final String name;
	
	private MasteryTwo(String id, String name) {
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
		return _1.id.equals(id) || _2.id.equals(id) || _3.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (MasteryTwo masteryTwo : MasteryTwo.values()) {
			if (masteryTwo.id.equalsIgnoreCase(id)) {
				return masteryTwo.name;
			}
		}
		return "";
	}

}
