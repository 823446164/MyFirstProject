package com.amarsoft.aecd.project.constant;

/**
 * 发生类型（PROJECT_CATALOG表）
 * @author bjmeng
 *
 */
public enum ProjectType {
	
	_1("1","新开发系统试"),
	_2("2","按月计价服务"),
	_3("3","原系统换代"),
	_4("4","任务计价"),
	;
	
	public final String id;
	public final String name;
	
	private ProjectType(String id, String name) {
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
		return _1.id.equals(id) || _2.id.equals(id)||_3.id.equals(id)||_4.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (ProjectType projectType : ProjectType.values()) {
			if (projectType.id.equalsIgnoreCase(id)) {
				return projectType.name;
			}
		}
		return "";
	}

}
