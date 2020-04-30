package com.amarsoft.aecd.project.constant;

/**
 * 项目状态（PROJECT_CATALOG表）
 * @author bjmeng
 *
 */
public enum ProjectStatus {
	
	_1("1","项目进行中"),
	_2("2","正常终结审批中"),
	_3("3","（用户）已完成待收款"),
	_4("4","项目全部结束")
	;
	
	public final String id;
	public final String name;
	
	private ProjectStatus(String id, String name) {
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
		for (ProjectStatus projectStatus : ProjectStatus.values()) {
			if (projectStatus.id.equalsIgnoreCase(id)) {
				return projectStatus.name;
			}
		}
		return "";
	}

}
