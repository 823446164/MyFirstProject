package com.amarsoft.aecd.acct.constant;

import java.util.ArrayList;
import java.util.List;

/**
 *基准利率类型
 * @author Amarsoft核算团队
 */
public interface BaseRateType {
    enum HomeCurrency implements BaseRateType{
        _01("01","贷款利率"),
        _I01("I01","出资方贷款利率"),
        _010("010","人行基准利率"),
        _020("020","公积金基准利率"),
        _030("030","贴现利率"),
        _035("035","LPR");
        public String id;
        public String name;
        
        private HomeCurrency(String id, String name){
            this.id = id;
            this.name = name;
        }
        
        /**
         * 判断当前输入的参数值是否是枚举的一个值
         * @param id
         * @return
         */
        public final static boolean isExist(String id) {
            return  _01.id.equals(id)
                    || _010.id.equals(id)
                    || _020.id.equals(id)
                    || _030.id.equals(id)
                    || _035.id.equals(id)
                    ;
        }    
    }
    enum ForeignCurrency implements BaseRateType{
        _040("040","Libor"),
        _050("050","Hibor"),
        _060("060","Sibor");
        
        public String id;
        public String name;
        
        private ForeignCurrency(String id, String name){
            this.id = id;
            this.name = name;
        }
        
        /**
         * 判断当前输入的参数值是否是枚举的一个值
         * @param id
         * @return
         */
        public final static boolean isExist(String id) {
            return  _040.id.equals(id)
                    || _050.id.equals(id)
                    || _060.id.equals(id)
                    ;
        }    
    }
    
    public static BaseRateType[] values() {
        List<BaseRateType> results = new ArrayList<BaseRateType>();
        for (BaseRateType.HomeCurrency homeCurrency : BaseRateType.HomeCurrency.values()) {
            results.add(homeCurrency);
        }
        for (BaseRateType.ForeignCurrency foreignCurrency : BaseRateType.ForeignCurrency.values()) {
            results.add(foreignCurrency);
        }
        return results.toArray(new BaseRateType[0]);
    }
    
    /**
     * 判断当前输入的参数值是否是枚举的一个值
     * @param id
     * @return
     */
    public static boolean isExist(String id) {
        
        for (BaseRateType.HomeCurrency homeCurrency : BaseRateType.HomeCurrency.values()) {
            if(homeCurrency.id.equals(id)) {
                return true;
            }
        }
        for (BaseRateType.ForeignCurrency foreignCurrency : BaseRateType.ForeignCurrency.values()) {
            if(foreignCurrency.id.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
