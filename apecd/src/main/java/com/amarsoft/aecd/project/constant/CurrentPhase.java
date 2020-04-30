package com.amarsoft.aecd.project.constant;

/**
 * 当前阶段（PROJECT_CATALOG表）
 * @author bjmeng
 *
 */
public enum CurrentPhase {
	
	Disabled("1","失效"),
	Effectived("2","有效"),
	;
	
	public final String id;
	public final String name;
	
	private CurrentPhase(String id, String name) {
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
		return Disabled.id.equals(id) || Effectived.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (CurrentPhase currentPhase : CurrentPhase.values()) {
			if (currentPhase.id.equalsIgnoreCase(id)) {
				return currentPhase.name;
			}
		}
		return "";
	}

}
