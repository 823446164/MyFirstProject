package com.amarsoft.app.ems.system.service.impl;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.aecd.system.constant.WorkFlag;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.addworknote.AddWorkNoteReq;
import com.amarsoft.app.ems.system.cs.dto.calendarallquery.CalendarAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendarallquery.CalendarAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.calendarmonthquery.CalendarMonthQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendarmonthquery.CalendarMonthQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.calendarmonthquery.MonthInfo;
import com.amarsoft.app.ems.system.cs.dto.calendarquery.CalendarQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendarquery.CalendarQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.calendarupdate.Calendar;
import com.amarsoft.app.ems.system.cs.dto.calendarupdate.CalendarUpdateReq;
import com.amarsoft.app.ems.system.cs.dto.calendaryearquery.CalendarYearQueryReq;
import com.amarsoft.app.ems.system.cs.dto.calendaryearquery.CalendarYearQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.deleteworknote.DeleteWorkNoteReq;
import com.amarsoft.app.ems.system.cs.dto.isworkingdate.IsWorkingDateReq;
import com.amarsoft.app.ems.system.cs.dto.isworkingdate.IsWorkingDateRsp;
import com.amarsoft.app.ems.system.cs.dto.worknotealldatequery.WorkNoteAllDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.worknotealldatequery.WorkNotesDate;
import com.amarsoft.app.ems.system.cs.dto.worknotedatequery.WorkNoteDateQueryReq;
import com.amarsoft.app.ems.system.cs.dto.worknotedatequery.WorkNoteDateQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.worknotedatequery.WorkNoteInfo;
import com.amarsoft.app.ems.system.cs.dto.worknotequery.WorkNoteQueryRsp;
import com.amarsoft.app.ems.system.entity.SysCalendar;
import com.amarsoft.app.ems.system.entity.UserInfo;
import com.amarsoft.app.ems.system.entity.WorkRecord;
import com.amarsoft.app.ems.system.service.CalendarService;

/**
 * 日历服务的接口实现类
 * @author xxu1
 *
 */
@Service
public class CalendarServiceImpl implements CalendarService {

    @Override
    public CalendarQueryRsp getCalendarInfo(CalendarQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        CalendarQueryRsp response = new CalendarQueryRsp();
        List<SysCalendar> calendarList = bomanager.loadBusinessObjects(SysCalendar.class, 
                "calendarType=:calendarType and curCalendar=:curCalendar", "calendarType", request.getCalendarType(), "curCalendar", request.getCurCalendar());
        
        if(CollectionUtils.isEmpty(calendarList)) 
            return response;
        SysCalendar calendar = calendarList.get(0);
        
        //设置响应报文的值
        response.setCalendarType(calendar.getCalendarType());
        response.setCurCalendar(calendar.getCurCalendar());
        response.setWorkFlag(calendar.getWorkFlag());
        return response;
    }
    
    @Override
    public CalendarYearQueryRsp getCalendarYear(CalendarYearQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        CalendarYearQueryRsp response = new CalendarYearQueryRsp();
        response.setCalendars(new ArrayList<com.amarsoft.app.ems.system.cs.dto.calendaryearquery.Calendar>());
        List<SysCalendar> calendarList = bomanager.loadBusinessObjects(SysCalendar.class, 0, Integer.MAX_VALUE, "CurCalendar like :CurYear", "CurYear", request.getYear() + "%").getBusinessObjects();
        if(!CollectionUtils.isEmpty(calendarList)) {
            for(SysCalendar calendar : calendarList) {
                com.amarsoft.app.ems.system.cs.dto.calendaryearquery.Calendar calendarResponse = new com.amarsoft.app.ems.system.cs.dto.calendaryearquery.Calendar();
                calendarResponse.setCalendarType(calendar.getCalendarType());
                calendarResponse.setCurCalendar(calendar.getCurCalendar());
                calendarResponse.setWorkFlag(calendar.getWorkFlag());
                response.getCalendars().add(calendarResponse);
            }
        } 
        return response;
    }
    
    @Override
    public CalendarAllQueryRsp getCalendarAll(CalendarAllQueryReq req) {
        
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SysCalendar> calendarList = null;
        if (StringUtils.isEmpty(req.getCalendarType())) {
            calendarList = bomanager.loadBusinessObjects(SysCalendar.class, 0, Integer.MAX_VALUE, "1 = 1").getBusinessObjects();
        } else {
            calendarList = bomanager.loadBusinessObjects(SysCalendar.class, 0, Integer.MAX_VALUE, "calendarType = :calendarType","calendarType",req.getCalendarType()).getBusinessObjects();
        }
        
        CalendarAllQueryRsp rsp = new CalendarAllQueryRsp();
        rsp.setCalendars(new ArrayList<com.amarsoft.app.ems.system.cs.dto.calendarallquery.Calendar>());
        if(!CollectionUtils.isEmpty(calendarList)) {
            for(SysCalendar calendar : calendarList) {
                com.amarsoft.app.ems.system.cs.dto.calendarallquery.Calendar calendarResponse = new com.amarsoft.app.ems.system.cs.dto.calendarallquery.Calendar();
                //设置响应报文的值
                calendarResponse.setCalendarType(calendar.getCalendarType());
                calendarResponse.setCurCalendar(calendar.getCurCalendar());
                calendarResponse.setWorkFlag(calendar.getWorkFlag());
                rsp.getCalendars().add(calendarResponse);
            }
        }
        return rsp;
    }

    /**
     * 修改日历的信息
     * @param request
     */
    @Override
    public void setCalendarInfo(CalendarUpdateReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        for (Calendar calendar : request.getCalendar()) {
            List<SysCalendar> calendarList = bomanager.loadBusinessObjects(SysCalendar.class, "calendarType=:calendarType and curCalendar=:curCalendar", "calendarType", calendar.getCalendarType(), "curCalendar", calendar.getCurCalendar());
            SysCalendar newCalendar  = null;
            if(CollectionUtils.isEmpty(calendarList)) {
                newCalendar = new SysCalendar();
            }else {
                newCalendar = calendarList.get(0);
            }
            
            //设置参数的值
            newCalendar.setCurCalendar(calendar.getCurCalendar());
            newCalendar.setCalendarType(calendar.getCalendarType());
            newCalendar.setWorkFlag(calendar.getWorkFlag());
            
            
            bomanager.updateBusinessObject(newCalendar);
        }
        //更新到数据库
        bomanager.updateDB();
    }

    @Override
    public CalendarMonthQueryRsp getCalendarMonth(CalendarMonthQueryReq request) {
        
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<SysCalendar> sysCalendars= bomanager.loadBusinessObjects(SysCalendar.class, 0, Integer.MAX_VALUE, "curCalendar like :curCalendar and calendarType =:calendarType order by curCalendar","curCalendar",request.getYear() + "%","calendarType",request.getCalendarType()).getBusinessObjects();
        CalendarMonthQueryRsp rsp = new CalendarMonthQueryRsp();//返回的json报文为12个月每月情况
        ArrayList<MonthInfo> monthInfos = new ArrayList<MonthInfo>(12);
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(java.util.Calendar.YEAR,Integer.valueOf(request.getYear()));

        for (int i = 1; i <= 12; i++) {
            if (!request.getYear().equals(calendar.get(java.util.Calendar.YEAR) + "")) {
                break;  
            }
            int dateFlag = 1;//天数计数器
            MonthInfo monthInfo = new MonthInfo();
            //设置时间
            calendar.set(java.util.Calendar.MONTH, i-1); 
            calendar.set(java.util.Calendar.DATE,dateFlag);
            int workdayCount = 0;//工作日计数器
            int offdayCount = 0;//休息日计数器
            int statutorydayCount = 0;//法定假期计数器
            String[] now = {""};
            while (calendar.get(java.util.Calendar.DATE)  < calendar.getActualMaximum(java.util.Calendar.DATE) ) {//每日操作
                // 拼接日期字符串 （日、月 < 10  前加0）
                if (!request.getYear().equals(calendar.get(java.util.Calendar.YEAR) + "")) {
                    break;  
                }
                calendar.set(java.util.Calendar.DATE,dateFlag++);//日期递增
                
                now[0] = calendar.get(java.util.Calendar.YEAR)+ "/" + (StringUtils.trimAllWhitespace(String.valueOf(calendar.get(java.util.Calendar.MONTH) + 1)).length() <= 1 ?( "0" + (calendar.get(java.util.Calendar.MONTH)+1)):(calendar.get(java.util.Calendar.MONTH)+1)) + "/" + (calendar.get(java.util.Calendar.DATE) < 10?"0"+(calendar.get(java.util.Calendar.DATE)):(calendar.get(java.util.Calendar.DATE)));
                Optional<SysCalendar> sysCalendarOptional = sysCalendars.stream().filter(syscalendar -> syscalendar.getCurCalendar().equals(now[0]) && syscalendar.getCalendarType().equals(request.getCalendarType())).findFirst();
                if (!sysCalendarOptional.isPresent()) {//表中无数据则区分周末工作日
                    int day = calendar.get(java.util.Calendar.DAY_OF_WEEK);
                    if(day == java.util.Calendar.SUNDAY || day == java.util.Calendar.SATURDAY) {
                        ++offdayCount;
                    }else {
                        ++workdayCount;
                    }
                }else {//以表中数据为准
                    if (sysCalendarOptional.get().getWorkFlag().equals(WorkFlag.WorkDay.id)) {
                        ++workdayCount;
                    }else if (sysCalendarOptional.get().getWorkFlag().equals(WorkFlag.Holiday.id)) {
                        ++offdayCount;
                    }else if (sysCalendarOptional.get().getWorkFlag().equals(WorkFlag.StatutoryHoliday.id)) {
                        ++statutorydayCount;
                    }
                }
            }
            //进行每月统计
            monthInfo.setMonth(i);
            monthInfo.setWorkdays(workdayCount);
            monthInfo.setOffdays(offdayCount);
            monthInfo.setStatutorydays(statutorydayCount);
            monthInfos.add(monthInfo);
        }
        rsp.setMonthInfos(monthInfos);
        return rsp;
    }

    @Override
    @Transactional
    public IsWorkingDateRsp isWorkingDate(IsWorkingDateReq req) {
        IsWorkingDateRsp rsp = new IsWorkingDateRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        List<SysCalendar> sysCalendar = bomanager.loadBusinessObjects(SysCalendar.class, "curCalendar =:curCalendar and calendarType =:calendarType","curCalendar",req.getCurCalendar(),"calendarType",req.getCalendarType());
        if (CollectionUtils.isEmpty(sysCalendar)) {//表中无数据则区分周末工作日
            DayOfWeek dow = DateHelper.getDate(req.getCurCalendar()).getDayOfWeek();
            if(dow != DayOfWeek.SUNDAY && dow != DayOfWeek.SATURDAY) {//1到5为工作日
                rsp.setIsWorkingDate(Boolean.TRUE);
            }else {
                rsp.setIsWorkingDate(Boolean.FALSE);
            }
        }else {//以表中数据为准
            if (sysCalendar.get(0).getWorkFlag().equals(WorkFlag.WorkDay.id)) {
                rsp.setIsWorkingDate(Boolean.TRUE);
            }else if (sysCalendar.get(0).getWorkFlag().equals(WorkFlag.Holiday.id) || sysCalendar.get(0).getWorkFlag().equals(WorkFlag.StatutoryHoliday.id)) {
                rsp.setIsWorkingDate(Boolean.FALSE);
            }else {
                rsp.setIsWorkingDate(Boolean.FALSE);
            }
        }
        return rsp;
    }
    
    @Override
    public void addWorkNote(AddWorkNoteReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserInfo ui = bomanager.keyLoadBusinessObject(UserInfo.class, GlobalShareContextHolder.getUserId());
        if(ui == null) {//数据库不存在该用户
            throw new ALSException("900920");//没有用户
        } else {
            WorkRecord eventCalendar = null;
            if(StringUtils.isEmpty(request.getWorkId())) {
                eventCalendar = new WorkRecord();
            } else {
                eventCalendar = bomanager.keyLoadBusinessObject(WorkRecord.class, request.getWorkId());
            }
            
            eventCalendar.setUserId(GlobalShareContextHolder.getUserId());
            eventCalendar.setWorkContent(request.getWorkContent());
            eventCalendar.setExecuteSituation(request.getExecuteSituation());
            eventCalendar.setWorkNodeDate(request.getCalendarDate());
            eventCalendar.setReason(request.getReason());
            eventCalendar.setRemark(request.getRemark());
            bomanager.updateBusinessObject(eventCalendar);
        }
        bomanager.updateDB();
    }

    @Override
    public void deleteWorkNote(DeleteWorkNoteReq deleteWorkNoteReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        WorkRecord eventCalendar = bomanager.keyLoadBusinessObject(WorkRecord.class, deleteWorkNoteReq.getWorkId());
        bomanager.deleteBusinessObject(eventCalendar);
        bomanager.updateDB();
    }

    @Override
    public WorkNoteDateQueryRsp getWorkNoteByDate(WorkNoteDateQueryReq workNoteByDateQueryReq) {
        WorkNoteDateQueryRsp workNoteRsp = new WorkNoteDateQueryRsp();
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        BusinessObjectAggregate<WorkRecord> EventCalendarObjects = null;
        int firstIndex = workNoteByDateQueryReq.getBegin();
        int pageSize = workNoteByDateQueryReq.getPageSize();
        
        EventCalendarObjects = bomanager.loadBusinessObjects(WorkRecord.class, firstIndex,pageSize,"workNodeDate = :workNodeDate and userId=:userId", "workNodeDate",workNoteByDateQueryReq.getDate(), "userId", GlobalShareContextHolder.getUserId());
        List<WorkRecord> eventCalendars= EventCalendarObjects.getBusinessObjects();
        Integer totalCount = EventCalendarObjects.getAggregate("count(*) as cnt ").getInt("cnt");
        List<WorkNoteInfo> workNoteInfos = new ArrayList<>();
        for(WorkRecord eventCalendar : eventCalendars) {
            WorkNoteInfo workNoteInfo = new WorkNoteInfo();
            workNoteInfo.setWorkId(eventCalendar.getWorkId());
            workNoteInfo.setUserId(eventCalendar.getUserId());
            workNoteInfo.setWorkContent(eventCalendar.getWorkContent());
            workNoteInfo.setExecuteSituation(eventCalendar.getExecuteSituation());
            workNoteInfo.setReason(eventCalendar.getReason());
            workNoteInfo.setRemark(eventCalendar.getRemark());
            workNoteInfos.add(workNoteInfo);
        }
        workNoteRsp.setTotalCount(totalCount);
        workNoteRsp.setWorkNoteInfos(workNoteInfos);
        return workNoteRsp;
    }
    
    @Override
    public WorkNoteQueryRsp getWorkNoteByWorkId(String workId) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        WorkRecord eventCalendar = bomanager.keyLoadBusinessObject(WorkRecord.class, workId);
        WorkNoteQueryRsp workNoteInfo = new WorkNoteQueryRsp();
        workNoteInfo.setWorkId(workId);
        workNoteInfo.setExecuteSituation(eventCalendar.getExecuteSituation());
        workNoteInfo.setWorkContent(eventCalendar.getWorkContent());
        workNoteInfo.setReason(eventCalendar.getReason());
        workNoteInfo.setRemark(eventCalendar.getRemark());
        return workNoteInfo;
    }

    @Override
    public WorkNoteAllDateQueryRsp getWorkNotesAllDate() {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        WorkNoteAllDateQueryRsp rsp = new WorkNoteAllDateQueryRsp();
        rsp.setCalendars(new ArrayList<WorkNotesDate>());
        BusinessObjectAggregate<BusinessObject> workNotesDates = bomanager.selectBusinessObjectsByNativeSql(0, Integer.MAX_VALUE, "select workNodeDate from SYS_WORK_RECORD where userId=:userId group by workNodeDate", "userId", GlobalShareContextHolder.getUserId());
        Optional<List<BusinessObject>> option = Optional.of(workNotesDates.getBusinessObjects());
        if (option.isPresent()) {
            rsp.setCalendars(
                option.get().stream().map(note -> {
                    WorkNotesDate workNotesDate = new WorkNotesDate();
                    workNotesDate.setWorkNodeDate(note.getString("WorkNodeDate"));
                    return workNotesDate;
                }).distinct()
                .collect(Collectors.toList())
            );
        }
        return rsp;
    }
}
