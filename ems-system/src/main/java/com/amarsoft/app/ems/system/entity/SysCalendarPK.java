package com.amarsoft.app.ems.system.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sys_calendar database table.
 * 
 */
@Embeddable
public class SysCalendarPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private String calendarType;

    private String curCalendar;


    public String getCurCalendar() {
        return curCalendar;
    }

    public void setCurCalendar(String curCalendar) {
        this.curCalendar = curCalendar;
    }

    public SysCalendarPK() {
    }

    public String getCalendarType() {
        return calendarType;
    }
    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SysCalendarPK)) {
            return false;
        }
        SysCalendarPK castOther = (SysCalendarPK)other;
        return 
            this.calendarType.equals(castOther.calendarType)
            && this.curCalendar.equals(castOther.curCalendar);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.calendarType.hashCode();
        hash = hash * prime + this.curCalendar.hashCode();
        
        return hash;
    }
}