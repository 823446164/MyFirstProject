/**
 * 查询指定日历信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
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
import com.amarsoft.app.ems.system.cs.dto.worknotequery.WorkNoteQueryReq;
import com.amarsoft.app.ems.system.cs.dto.worknotequery.WorkNoteQueryRsp;

public interface CalendarController {
    @PostMapping(value = "/system/getcalendarinfo", name="查询指定日历信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<CalendarQueryRsp>> calendarQuery(@RequestBody @Valid RequestMessage<CalendarQueryReq> reqMsg);
    @PostMapping(value = "/system/getcalendaryear", name="查询指定年份的日历信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<CalendarYearQueryRsp>> calendarYearQuery(@RequestBody @Valid RequestMessage<CalendarYearQueryReq> reqMsg);
    @PostMapping(value = "/system/getcalendarmonth", name="查询每月概况", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<CalendarMonthQueryRsp>> calendarMonthQuery(@RequestBody @Valid RequestMessage<CalendarMonthQueryReq> reqMsg);
    @PostMapping(value = "/system/getcalendarall", name="获取所有日历的信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<CalendarAllQueryRsp>> calendarAllQuery(@RequestBody @Valid RequestMessage<CalendarAllQueryReq> reqMsg);
    @PostMapping(value = "/system/setcalendarinfo", name="修改指定日历信息", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> calendarUpdate(@RequestBody @Valid RequestMessage<CalendarUpdateReq> reqMsg);
    @PostMapping(value = "/system/isworkingdate", name="查询是否为工作日", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<IsWorkingDateRsp>> isWorkingDate(@RequestBody @Valid RequestMessage<IsWorkingDateReq> reqMsg);
    @PostMapping(value = "/system/addworknote", name="新增工作笔记", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> addWorkNote(@RequestBody @Valid RequestMessage<AddWorkNoteReq> reqMsg);
    @PostMapping(value = "/system/deleteworknote", name="删除工作笔记", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> deleteWorkNote(@RequestBody @Valid RequestMessage<DeleteWorkNoteReq> reqMsg);
    @PostMapping(value = "/system/worknotequerybydate", name="按日期查询工作笔记", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<WorkNoteDateQueryRsp>> workNoteDateQuery(@RequestBody @Valid RequestMessage<WorkNoteDateQueryReq> reqMsg);
    @PostMapping(value = "/system/getworknotesalldate", name="查询所有工作笔记日期", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<WorkNoteAllDateQueryRsp>> workNoteAllDateQuery();
    @PostMapping(value = "/system/worknotequery", name="查询工作笔记", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<WorkNoteQueryRsp>> workNoteQuery(@RequestBody @Valid RequestMessage<WorkNoteQueryReq> reqMsg);
}
