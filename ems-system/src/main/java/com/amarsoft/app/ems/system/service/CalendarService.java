package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.addworknote.AddWorkNoteReq;
import com.amarsoft.app.ems.system.cs.dto.calendarallquery.CalendarAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendarallquery.CalendarAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.calendarmonthquery.CalendarMonthQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendarmonthquery.CalendarMonthQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.calendarquery.CalendarQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendarquery.CalendarQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.calendarupdate.CalendarUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.calendaryearquery.CalendarYearQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendaryearquery.CalendarYearQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteworknote.DeleteWorkNoteReq;
import com.amarsoft.app.ems.system.cs.dto.isworkingdate.IsWorkingDateReq;
import com.amarsoft.app.ems.system.cs.dto.isworkingdate.IsWorkingDateRsp;
import com.amarsoft.app.ems.system.cs.dto.worknotealldatequery.WorkNoteAllDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.worknotedatequery.WorkNoteDateQueryReq;
import com.amarsoft.app.ems.system.cs.dto.worknotedatequery.WorkNoteDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.worknotequery.WorkNoteQueryRsp;

/**
 * 日历服务的接口
 * @author xxu1
 */
public interface CalendarService {
    /**
     * 获取指定的日历的信息
     * @param request
     * @return
     */
    CalendarQueryRsp getCalendarInfo(CalendarQueryReq request);
    
    /**
     * 获取所有日历的信息
     * @param requestrequest) {
            throw new ALSException("900501", "查询指定年份的日历信息接口请求对象不允许为空");
        }
     * @return
     */
    CalendarYearQueryRsp getCalendarYear(CalendarYearQueryReq request);
    
    /**
     * 获取所有日历的信息
     * @param calendarAllQueryReq 
     * @return
     */
    CalendarAllQueryRsp getCalendarAll(CalendarAllQueryReq request);
    
    /**
     * 修改日历的信息
     * @param bomanager
     * @param request
     */
    void setCalendarInfo(CalendarUpdateReq request);

    /**
     * 查询每月的信息
     * @param bomanager
     * @param request
     */
    CalendarMonthQueryRsp getCalendarMonth(CalendarMonthQueryReq request);

    /**
     * 判定是否为工作日
     * @param bomanager
     * @param request
     */
    IsWorkingDateRsp isWorkingDate(IsWorkingDateReq message);
    
    /**
     * 新增工作笔记
     * @param bomanager
     * @param request
     */
    void addWorkNote(AddWorkNoteReq request);
    
    /**
     * 删除工作笔记
     * @param deleteWorkNoteReq
     */
    void deleteWorkNote(DeleteWorkNoteReq deleteWorkNoteReq);
    
    /**
     * 根据日期获取工作笔记
     * @param workNoteByDateQueryReq
     * @return
     */
    WorkNoteDateQueryRsp getWorkNoteByDate(WorkNoteDateQueryReq workNoteByDateQueryReq);
    
    /**
     * 根据主键获取工作笔记
     * @param workNoteByDateQueryReq
     * @return
     */
    WorkNoteQueryRsp getWorkNoteByWorkId(String workId);
    
    /**
     * 获取工作笔记所有日期
     * @param workNoteByDateQueryReq
     * @return
     */
    WorkNoteAllDateQueryRsp getWorkNotesAllDate();
}
