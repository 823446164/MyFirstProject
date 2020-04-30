package com.amarsoft.aecd.project.constant;

/**
 * 保存状态（PROJECT_CATALOG表)
 * @author bjmeng
 *
 */
public enum ProjectSaveStatus {
	
	Save("1","保存"),
	Staging("2","暂存"),
	;
	
	public final String id;
	public final String name;
	
	private ProjectSaveStatus(String id, String name) {
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
		return Save.id.equals(id) || Staging.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (ProjectSaveStatus projectSaveStatus : ProjectSaveStatus.values()) {
			if (projectSaveStatus.id.equalsIgnoreCase(id)) {
				return projectSaveStatus.name;
			}
		}
		return "";
	}

}
