package com.amarsoft.aecd.system.constant;

/**
 * 部门或团队状态（SYS_ORG_INFO表/SYS_TEAM_INFO表）
 * @author bjmeng
 *
 */
public enum OrgStatus {
	
	Completed("1","完成"),
	Disabled("2","停用"),
	;
	
	public final String id;
	public final String name;
	
	private OrgStatus(String id, String name) {
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
		return Completed.id.equals(id) || Disabled.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (OrgStatus orgStatus : OrgStatus.values()) {
			if (orgStatus.id.equalsIgnoreCase(id)) {
				return orgStatus.name;
			}
		}
		return "";
	}
	

}
