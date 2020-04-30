package com.amarsoft.aecd.system.constant;

/**
 * 适用要求类别（SYS_TEAM_USER表）
 * @author bjmeng
 *
 */
public enum ApplyType {

	_1("1", "精通/熟练使用/熟悉/了解"),
	_2("2", "精通/熟悉/了解"),
	_3("3", "优秀/良好/一般"),
	;

	public final String id;
	public final String name;

	private ApplyType(String id, String name) {
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
		return _1.id.equals(id) || _2.id.equals(id)||_3.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (ApplyType applyType : ApplyType.values()) {
			if (applyType.id.equalsIgnoreCase(id)) {
				return applyType.name;
			}
		}
		return "";
	}

}
