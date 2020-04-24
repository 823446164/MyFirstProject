package com.amarsoft.app.ems.system.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.adduserevent.AddUserEventReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryRsp;
import com.amarsoft.app.ems.system.entity.UserEvent;
import com.amarsoft.app.ems.system.entity.UserEventBase;
import com.amarsoft.app.ems.system.service.UserEventService;
import com.amarsoft.app.ems.system.util.UserEventHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户事件服务逻辑的处理类
 * @author hzhang23
 */
@Slf4j
@Service
public class UserEventServiceImpl implements UserEventService {

    @Autowired
    UserEventHelper userEventHelper;
    
    @Override
    public UserEventQueryRsp userEventQuery(UserEventQueryReq req) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        UserEventQueryRsp rsp = new UserEventQueryRsp();
        BusinessObjectAggregate<UserEventBase> userEvents = null;
        String[] eventTypes = null;
        if (!CollectionUtils.isEmpty(req.getUserEventType())) {
            eventTypes = req.getUserEventType().stream().map(s -> s).toArray(String[]::new);
        }
        if (CollectionUtils.isEmpty(req.getUserEventType())) {
            if (StringUtils.isEmpty(req.getBeginTime()) || StringUtils.isEmpty(req.getEndTime())) {
                userEvents = bomanager.loadBusinessObjectsContainHistory(UserEvent.class, req.getBegin(), req.getPageSize(), "userId = :userId and status = :status order by eventTime",
                        "userId", req.getUserId(), "status", Status.Valid.id);
            }else {
                userEvents = bomanager.loadBusinessObjectsContainHistory(UserEvent.class, req.getBegin(), req.getPageSize(), "userId = :userId and status = :status  and eventTime between :dateStartTime and :dateEndTime order by eventTime",
                        "userId", req.getUserId(), "status", Status.Valid.id, "dateStartTime", dateFormate(req.getBeginTime()), "dateEndTime", dateFormate(req.getEndTime()));
            }
        } else {
            if (StringUtils.isEmpty(req.getBeginTime()) || StringUtils.isEmpty(req.getEndTime())) {
                userEvents = bomanager.loadBusinessObjectsContainHistory(UserEvent.class,req.getBegin(),req.getPageSize(),"userId = :userId and status = :status and userEventType in ( :userEventType ) order by eventTime",
                        "userId",req.getUserId(),"status",Status.Valid.id,"userEventType",eventTypes);
            }else {
                userEvents = bomanager.loadBusinessObjectsContainHistory(UserEvent.class,req.getBegin(),req.getPageSize(),"userId = :userId and status = :status and userEventType in ( :userEventType )  and eventTime between :dateStartTime and :dateEndTime order by eventTime",
                        "userId",req.getUserId(),"status",Status.Valid.id,"userEventType",eventTypes,"dateStartTime",dateFormate(req.getBeginTime()),"dateEndTime",dateFormate(req.getEndTime()));
            }
        }
        rsp.setTotalCount(userEvents.getAggregate(" count(eventId) as cnt").getInt("cnt"));
        
        rsp.setEvents(new ArrayList<com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEvent>());
        userEvents.getBusinessObjects().stream().forEach(event -> {
            com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEvent userEvent = new com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEvent();
            userEvent.setBrowser(event.getBrowser());
            userEvent.setEventTime(event.getEventTime().format(DateTimeFormatter.ofPattern(FormatType.DateTimeLongFormat.format)));
            userEvent.setIpAddress(event.getIpAddress());
            userEvent.setUserEventType(event.getUserEventType());
            userEvent.setLog(event.getLog());
            userEvent.setOs(event.getOs());
            rsp.getEvents().add(userEvent);
        });
        
        return rsp;
    }

    @Override
    public void addUserEvent(AddUserEventReq req) {
        userEventHelper.setUserEvent(req.getUserEventType(), req.getUserId(),req.getOrgId(), DateHelper.getDateTime(DateHelper.getBusinessDateLongTime()),
                req.getLog(),req.getOs(),req.getBrowser(), req.getIpAddress(),Status.Valid.id);
    }

    private LocalDateTime dateFormate(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format));
    }
    
}
