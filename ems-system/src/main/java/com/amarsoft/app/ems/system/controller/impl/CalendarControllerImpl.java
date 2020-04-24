package com.amarsoft.app.ems.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amarsoft.amps.acsc.annotation.CodeQuery;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.system.controller.CalendarController;
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
import com.amarsoft.app.ems.system.service.CalendarService;

import lombok.extern.slf4j.Slf4j;

/**
 * 日历服务的处理类
 * @author 核算产品团队
 */
@Slf4j
@RestController
public class CalendarControllerImpl implements CalendarController {
    
    @Autowired
    CalendarService calendarService;
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<CalendarQueryRsp>> calendarQuery(@RequestBody @Valid RequestMessage<CalendarQueryReq> reqMsg){
        try {
            CalendarQueryReq request = reqMsg.getMessage();
            CalendarQueryRsp response = calendarService.getCalendarInfo(request);
            return new ResponseEntity<ResponseMessage<CalendarQueryRsp>>(new ResponseMessage<CalendarQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<CalendarQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900501", e.getMessage());
            return new ResponseEntity<ResponseMessage<CalendarQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<CalendarYearQueryRsp>> calendarYearQuery(@RequestBody @Valid RequestMessage<CalendarYearQueryReq> reqMsg){
        CalendarYearQueryReq request = null;
        try {
            request = reqMsg.getMessage();
            CalendarYearQueryRsp response = calendarService.getCalendarYear(request);
            return new ResponseEntity<ResponseMessage<CalendarYearQueryRsp>>(new ResponseMessage<CalendarYearQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            ResponseMessage<CalendarYearQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900501", e.getMessage());
            return new ResponseEntity<ResponseMessage<CalendarYearQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @CodeQuery
    public ResponseEntity<ResponseMessage<CalendarAllQueryRsp>> calendarAllQuery(@RequestBody @Valid RequestMessage<CalendarAllQueryReq> reqMsg){
        try {
            CalendarAllQueryRsp response = calendarService.getCalendarAll(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<CalendarAllQueryRsp>>(new ResponseMessage<CalendarAllQueryRsp>(response), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error(e.getMessage(), e);
            }
            ResponseMessage<CalendarAllQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900501", e.getMessage());
            return new ResponseEntity<ResponseMessage<CalendarAllQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> calendarUpdate(@RequestBody @Valid RequestMessage<CalendarUpdateReq> reqMsg){
        try {
            calendarService.setCalendarInfo(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch(Exception e) {
            if(log.isErrorEnabled()) {
                log.error("[请求报文：" + reqMsg.toString() + "]", e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900502", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<CalendarMonthQueryRsp>> calendarMonthQuery(@RequestBody @Valid RequestMessage<CalendarMonthQueryReq> reqMsg){
        ResponseMessage<CalendarMonthQueryRsp> rspMsg = null;
        try {
            CalendarMonthQueryRsp rsp = calendarService.getCalendarMonth(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<CalendarMonthQueryRsp>>(new ResponseMessage<CalendarMonthQueryRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询每月概况请求报文："+ reqMsg.toString(), e);
            }
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900507",e.getMessage());
            return new ResponseEntity<ResponseMessage<CalendarMonthQueryRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<IsWorkingDateRsp>> isWorkingDate(@RequestBody @Valid RequestMessage<IsWorkingDateReq> reqMsg) {
        ResponseMessage<IsWorkingDateRsp> rspMsg = null;
        try {
            IsWorkingDateRsp rsp = calendarService.isWorkingDate(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<IsWorkingDateRsp>>(new ResponseMessage<IsWorkingDateRsp>(rsp), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("查询每月概况请求报文："+ reqMsg.toString(), e);
            }
            
            rspMsg = ResponseMessage.getResponseMessageFromException(e, "900507",e.getMessage());
            return new ResponseEntity<ResponseMessage<IsWorkingDateRsp>>(rspMsg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 新增工作笔记
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> addWorkNote(@RequestBody @Valid RequestMessage<AddWorkNoteReq> reqMsg) {
        try {
            AddWorkNoteReq workNoteReq = reqMsg.getMessage();
            calendarService.addWorkNote(workNoteReq);
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("工作笔记新增报文："+ reqMsg.toString(), e);
            }
            //事务回滚 
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900507",e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    /**
     * 删除工作笔记
     */
    @Override
    @Transactional
    public ResponseEntity<ResponseMessage<Object>> deleteWorkNote(@RequestBody @Valid RequestMessage<DeleteWorkNoteReq> reqMsg) {
        try {
            calendarService.deleteWorkNote(reqMsg.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(new ResponseMessage<Object>(), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("工作笔记删除请求报文：" + reqMsg.toString(), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResponseMessage<Object> hrb = ResponseMessage.getResponseMessageFromException(e, "900305", e.getMessage());
            return new ResponseEntity<ResponseMessage<Object>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<WorkNoteDateQueryRsp>> workNoteDateQuery(@RequestBody @Valid RequestMessage<WorkNoteDateQueryReq> reqMsg){
        try {
            WorkNoteDateQueryReq workNoteByDateQueryReq= reqMsg.getMessage();
            WorkNoteDateQueryRsp response = calendarService.getWorkNoteByDate(workNoteByDateQueryReq);
            return new ResponseEntity<ResponseMessage<WorkNoteDateQueryRsp>>(new ResponseMessage<WorkNoteDateQueryRsp>(response), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("根据日期查询工作笔记请求报文：" + reqMsg.toString(), e);
            }
            ResponseMessage<WorkNoteDateQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900501", e.getMessage());
            return new ResponseEntity<ResponseMessage<WorkNoteDateQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<WorkNoteAllDateQueryRsp>> workNoteAllDateQuery(){
        try {
            WorkNoteAllDateQueryRsp response = calendarService.getWorkNotesAllDate();
            return new ResponseEntity<ResponseMessage<WorkNoteAllDateQueryRsp>>(new ResponseMessage<WorkNoteAllDateQueryRsp>(response), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("根据日期查询工作笔记请求报文：", e);
            }
            ResponseMessage<WorkNoteAllDateQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900501", e.getMessage());
            return new ResponseEntity<ResponseMessage<WorkNoteAllDateQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseMessage<WorkNoteQueryRsp>> workNoteQuery(@RequestBody @Valid RequestMessage<WorkNoteQueryReq> reqMsg){
        try {
            WorkNoteQueryReq workNoteByDateQueryReq = reqMsg.getMessage();
            WorkNoteQueryRsp response = calendarService.getWorkNoteByWorkId(workNoteByDateQueryReq.getWorkId());
            return new ResponseEntity<ResponseMessage<WorkNoteQueryRsp>>(new ResponseMessage<WorkNoteQueryRsp>(response), HttpStatus.OK);
        } catch (Exception e) {
            if(log.isErrorEnabled()) {
                log.error("根据主键查询工作笔记请求报文：" + reqMsg.toString(), e);
            }
            ResponseMessage<WorkNoteQueryRsp> hrb = ResponseMessage.getResponseMessageFromException(e, "900501", e.getMessage());
            return new ResponseEntity<ResponseMessage<WorkNoteQueryRsp>>(hrb,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}