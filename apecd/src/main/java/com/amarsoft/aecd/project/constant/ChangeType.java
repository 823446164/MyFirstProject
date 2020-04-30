package com.amarsoft.aecd.project.constant;

/**
 * 变更类型（PROJECT_MEMBER_CHANGE表）
 * @author amarsoft
 *
 */
public enum ChangeType {

	AddMember("1","添加成员"),
	DeleteMember("2","删除成员"),
	;
	
	public final String id;
	public final String name;
	
	private ChangeType(String id, String name) {
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
		return AddMember.id.equals(id) || DeleteMember.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (ChangeType changeType : ChangeType.values()) {
			if (changeType.id.equalsIgnoreCase(id)) {
				return changeType.name;
			}
		}
		return "";
	}
	
	
}
