package com.amarsoft.aecd.common.constant;

import com.amarsoft.aecd.acct.constant.YearBaseDay;

/**
 * 币种
 * @author Amarsoft核算团队
 */
public enum Currency {
    CNY("CNY","人民币", YearBaseDay._360.day, ScaleType.Money2.scale),
    EUR("EUR","欧元", YearBaseDay._360.day, ScaleType.Money2.scale),
    GBP("GBP","英镑", YearBaseDay._365.day, ScaleType.Money2.scale),
    HKD("HKD","港币", YearBaseDay._365.day, ScaleType.Money2.scale),
    JPY("JPY","日元", YearBaseDay._360.day, ScaleType.Money0.scale),
    USD("USD","美元", YearBaseDay._360.day, ScaleType.Money2.scale),
    AUD("AUD","澳大利亚元",YearBaseDay._365.day,ScaleType.Money2.scale),
    BDT("BDT","孟加拉国塔卡",YearBaseDay._365.day,ScaleType.Money2.scale),
    BEF("BEF","比利时法郎",YearBaseDay._360.day,ScaleType.Money2.scale),
    BUK("BUK","缅甸币",YearBaseDay._360.day,ScaleType.Money2.scale),
    CAD("CAD","加拿大元",YearBaseDay._365.day,ScaleType.Money2.scale),
    CHF("CHF","瑞士法郎",YearBaseDay._360.day,ScaleType.Money2.scale),
    DEM("DEM","德国马克",YearBaseDay._360.day,ScaleType.Money2.scale),
    DKK("DKK","丹麦克朗",YearBaseDay._360.day,ScaleType.Money2.scale),
    EGP("EGP","埃及镑",YearBaseDay._360.day,ScaleType.Money2.scale),
    ESP("ESP","西班牙比塞塔",YearBaseDay._360.day,ScaleType.Money0.scale),
    FRF("FRF","法国法郎",YearBaseDay._360.day,ScaleType.Money2.scale),
    IDR("IDR","印度尼西亚卢比",YearBaseDay._360.day,ScaleType.Money0.scale),
    INR("INR","印度罗比",YearBaseDay._365.day,ScaleType.Money2.scale),
    IQD("IQD","伊拉克地纳尔",YearBaseDay._360.day,ScaleType.Money2.scale),
    IRR("IRR","伊朗里亚尔",YearBaseDay._360.day,ScaleType.Money0.scale),
    ITL("ITL","意大利里拉",YearBaseDay._360.day,ScaleType.Money0.scale),
    KPW("KPW","朝鲜币",YearBaseDay._360.day,ScaleType.Money0.scale),
    KRW("KRW","韩元",YearBaseDay._360.day,ScaleType.Money0.scale),
    MNT("MNT","蒙古图格里克",YearBaseDay._360.day,ScaleType.Money0.scale),
    MOP("MOP","澳门元",YearBaseDay._360.day,ScaleType.Money2.scale),
    MYR("MYR","马来西亚林吉特",YearBaseDay._360.day,ScaleType.Money2.scale),
    PHP("PHP","菲律宾比索",YearBaseDay._360.day,ScaleType.Money2.scale),
    PKR("PKR","巴基斯坦卢比",YearBaseDay._360.day,ScaleType.Money2.scale),
    RUB("RUB","俄国卢布",YearBaseDay._360.day,ScaleType.Money0.scale),
    SEK("SEK","瑞典克朗",YearBaseDay._360.day,ScaleType.Money0.scale),
    SGD("SGD","新加坡元",YearBaseDay._365.day,ScaleType.Money2.scale),
    TWD("TWD","台湾元",YearBaseDay._360.day,ScaleType.Money2.scale),
    VND("VND","越南盾",YearBaseDay._360.day,ScaleType.Money0.scale),
    
    ;
    public String id;
    public String name;
    public int yearBaseDay;
    public int scale;
    
    private Currency(String id, String name, int yearBaseDay, int scale){
        this.id = id;
        this.name = name;
        this.yearBaseDay = yearBaseDay;
        this.scale = scale;
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        return CNY.id.equals(id)
                ||EUR.id.equals(id)
                ||GBP.id.equals(id)
                ||HKD.id.equals(id)
                ||JPY.id.equals(id)
                ||USD.id.equals(id)
                ||AUD.id.equals(id)
                ||BDT.id.equals(id)
                ||BEF.id.equals(id)
                ||BUK.id.equals(id)
                ||CAD.id.equals(id)
                ||CHF.id.equals(id)
                ||DEM.id.equals(id)
                ||DKK.id.equals(id)
                ||EGP.id.equals(id)
                ||ESP.id.equals(id)
                ||FRF.id.equals(id)
                ||IDR.id.equals(id)
                ||INR.id.equals(id)
                ||IQD.id.equals(id)
                ||IRR.id.equals(id)
                ||ITL.id.equals(id)
                ||KPW.id.equals(id)
                ||KRW.id.equals(id)
                ||MNT.id.equals(id)
                ||MOP.id.equals(id)
                ||MYR.id.equals(id)
                ||PHP.id.equals(id)
                ||PKR.id.equals(id)
                ||RUB.id.equals(id)
                ||SEK.id.equals(id)
                ||SGD.id.equals(id)
                ||TWD.id.equals(id)
                ||VND.id.equals(id);
    }
    
    
    public static int getYearBaseDay(String id) {
        
        for(Currency currency : Currency.values()) {
            if(currency.id.equals(id)) {
                return currency.yearBaseDay;
            }
        }
        return YearBaseDay._360.day;
    }
    
    public static int getScale(String id) {
        
        for(Currency currency : Currency.values()) {
            if(currency.id.equals(id)) {
                return currency.scale;
            }
        }
        return ScaleType.Money2.scale;
    }
}
