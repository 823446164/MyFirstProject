package com.amarsoft.aecd.acct.constant;
/**
 * 纳税人类型
 * @author xxu1
 */
public enum TaxpayerType {
    Common_Taxpayer("01", "增值税一般纳税人"),
    Small_Taxpayer("02", "增值税小规模纳税人"),
    Government("03", "行政事业单位"),
    Outer_Same("04","境外同业"),
    Outer_Other("05","境外其他机构"),
    ;
    public final String id;
    public final String name;
    
    private TaxpayerType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return Common_Taxpayer.id.equals(id) 
                || Small_Taxpayer.id.equals(id)
                || Government.id.equals(id)
                || Outer_Same.id.equals(id)
                || Outer_Other.id.equals(id);
    }
}
