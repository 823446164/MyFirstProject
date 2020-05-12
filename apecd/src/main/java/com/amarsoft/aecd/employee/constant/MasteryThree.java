/*
 * 文件名：MasteryThree.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：指标类型（RANK_STANDARD_ITEMS表）-掌握程度三类:优秀/良好/一般
 * 修改人：yqchen1
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.employee.constant;

public enum MasteryThree {
	
	_1("1","一般"),
	_2("2","良好"),
	_3("3","优秀"),
	;
	
	public final String id;
	public final String name;
	
	private MasteryThree(String id, String name) {
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
		for (MasteryThree masteryThree : MasteryThree.values()) {
			if (masteryThree.id.equalsIgnoreCase(id)) {
				return masteryThree.name;
			}
		}
		return "";
	}

}
