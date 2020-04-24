package com.amarsoft.app.ems.system.service;

import javax.validation.Valid;

import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryReq;
import com.amarsoft.app.ems.system.cs.dto.allauthchangesquery.AllAuthChangesQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryReq;
import com.amarsoft.app.ems.system.cs.dto.alllogininfoquery.AllLoginInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.allsecurityinfoquery.AllSecurityInfoQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlinenumquery.OnlineNumQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListReq;
import com.amarsoft.app.ems.system.cs.dto.onlineuserlist.OnlineUserListRsp;

/**
 * 安全审计的处理接口
 * @author hzhang23
 */
public interface AuditService {


    /**
     * 安全信息统计
     * @return
     */
    AllSecurityInfoQueryRsp allSecurityInfoQuery();
    
    

    /**
     * 在线人数
     * @return OnlineNumQueryRsp
     */
    OnlineNumQueryRsp onlineNumQuery();
    
    /**
     * 查询所有登录信息
     * @param allLoginInfoQueryReq 
     * @return
     */
    AllLoginInfoQueryRsp allLoginInfoQuery(AllLoginInfoQueryReq allLoginInfoQueryReq);
    
    /**
     * 查询所有权限变更
     * @param allAuthChangesQueryReq 
     * @return
     */
    AllAuthChangesQueryRsp allAuthChangesQuery(AllAuthChangesQueryReq allAuthChangesQueryReq);


    /**
     * 在线用户查询
     * @param onlineUserListReq
     * @return
     */
    OnlineUserListRsp getOnlineUserList(@Valid OnlineUserListReq onlineUserListReq);
}