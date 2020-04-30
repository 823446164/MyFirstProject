package com.amarsoft.aecd.parameter.constant;

/**
 * 指标类型（RANK_STANDARD_ITEMS表）
 * @author bjmeng
 *
 */
public enum RankType {
	
	Develop("1","开发"),
	Management("2","管理"),
	;
	
	public final String id;
	public final String name;
	
	private RankType(String id, String name) {
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
		return Develop.id.equals(id) || Management.id.equals(id);
	}

	/**
	 * 判断当前输入的参数值是否是枚举的一个值
	 * 
	 * @param id
	 * @return
	 */
	public static String getNameById(String id) {
		for (RankType rankType : RankType.values()) {
			if (rankType.id.equalsIgnoreCase(id)) {
				return rankType.name;
			}
		}
		return "";
	}

}
