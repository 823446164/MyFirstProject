package com.amarsoft.app.ems.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.CalendarType;
import com.amarsoft.aecd.system.constant.WorkFlag;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

/**
 * 系统日历
 */
@Entity
@Description("系统日历")
@Table(name="SYS_CALENDAR")
@IdClass(SysCalendarPK.class)
public class SysCalendar extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("日历类型")
    @Id
    @Enum(CalendarType.class)
    @Column(name = "CALENDARTYPE",length=10)
    private String calendarType;

    @Description("当前日期")
    @Id
    @Column(name = "CURCALENDAR",length=10)
    private String curCalendar;

    @Description("是否工作日（大陆）1:工作日、2：节假日、3：法定假")
    @Enum(WorkFlag.class)
    @Column(name = "WORKFLAG",length=10)
    private String workFlag;

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }


    public String getCurCalendar() {
        return curCalendar;
    }

    public void setCurCalendar(String curCalendar) {
        this.curCalendar = curCalendar;
    }
    
    public String getWorkFlag() {
        return workFlag;
    }

    public void setWorkFlag(String workFlag) {
        this.workFlag = workFlag;
    }
}