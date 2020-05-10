/*
 * 文件名：MasteryTwo.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：指标类型（EMPLOYEE_RANK表）-评定职级层
 * 修改人：yqchen1
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.aecd.employee.constant;

public enum EvaluateRankLevel {
	
	_1("1","高级管理层"),
	_2("2","技术专家层"),
	_3("3","项目管理层"),
	_4("4","项目执行层"),
	;
	
	public final String id;
	public final String name;
	
	private EvaluateRankLevel(String id, String name) {
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
		for (EvaluateRankLevel evaluateRankLevel : EvaluateRankLevel.values()) {
			if (evaluateRankLevel.id.equalsIgnoreCase(id)) {
				return evaluateRankLevel.name;
			}
		}
		return "";
	}

}
