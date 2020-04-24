package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.adduserevent.AddUserEventReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryReq;
import com.amarsoft.app.ems.system.cs.dto.usereventquery.UserEventQueryRsp;

/**
 * 用户事件服务的处理接口
 * @author hzhang23
 */
public interface UserEventService {
    /**
     * 用户事件查询
     * @param message
     * @return
     */
    UserEventQueryRsp userEventQuery(UserEventQueryReq message);
    /**
     * 新增用户事件
     * @param message
     * @return
     */
    void addUserEvent(AddUserEventReq message);
}