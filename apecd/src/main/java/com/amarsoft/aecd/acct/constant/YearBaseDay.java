package com.amarsoft.aecd.acct.constant;

/**
 * 年基准天数
 * @author xjzhao
 */
public enum YearBaseDay {
    
    _360(360),
    _365(365),
    ;
    
    public final int day;
    
    private YearBaseDay(int day){
        this.day = day;
    }
}
