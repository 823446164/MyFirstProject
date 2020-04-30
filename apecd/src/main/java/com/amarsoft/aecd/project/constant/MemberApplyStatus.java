package com.amarsoft.aecd.project.constant;

/**
 * 申请状态（PROJECT_MEMBER_CHANGE_APPLY表）
 * @author bjmeng
 *
 */
public enum MemberApplyStatus {
	
	Applying("1","申请中"),
	Cancel("2","取消申请"),
	;
	
	public final String id;
	public final String name;
	
	private MemberApplyStatus(String id, String name) {
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
		return Applying.id.equals(id) || Cancel.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (MemberApplyStatus memberApplyStatus : MemberApplyStatus.values()) {
			if (memberApplyStatus.id.equalsIgnoreCase(id)) {
				return memberApplyStatus.name;
			}
		}
		return "";
	}

}
