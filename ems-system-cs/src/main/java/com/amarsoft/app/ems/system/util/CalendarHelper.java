package com.amarsoft.app.ems.system.util;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.aecd.system.constant.CalendarType;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectCache;
import com.amarsoft.app.ems.system.cs.client.CalendarClient;
import com.amarsoft.app.ems.system.cs.dto.isworkingdate.IsWorkingDateReq;


/**
 * 机构、即其他信息缓存，可根据需要添加
 * 
 * @author hzhang23
 */
public final class CalendarHelper{
    private static CalendarClient calendarClient = SpringHelper.getBean(CalendarClient.class);
    private static BusinessObjectCache cache = new BusinessObjectCache(1024);
    
    /**
     * 清理缓存
     */
    public static void clear()
    {
        cache = new BusinessObjectCache(1024);
    }
    
    /**
     * 判断是否为工作日，默认查询大陆时区
     * @param date
     * @return
     */
    public static boolean isWorkingDate(String date) {
        return isWorkingDate(date, CalendarType.Land.id);
    }
    
    /**
     * 判断是否为工作日，传入CalendarType枚举类中的日历类型
     * @param date
     * @return
     */
    public static boolean isWorkingDate(String date,String calendarType) {
        Boolean calendarCache = (Boolean) cache.getCacheObject(date + "-" + calendarType);
        if(calendarCache == null){
            IsWorkingDateReq req = new IsWorkingDateReq();
            req.setCurCalendar(date);
            req.setCalendarType(calendarType);
            Boolean isworkingDate = calendarClient.isWorkingDate(new RequestMessage<IsWorkingDateReq>(req)).getBody().getMessage().getIsWorkingDate();
            cache.setCache(date + "-" + calendarType, isworkingDate);
        }
        return (boolean) cache.getCacheObject(date + "-" + calendarType);
    }
    
}

