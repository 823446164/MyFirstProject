package com.amarsoft.app.ems.system.service;

import com.amarsoft.app.ems.system.cs.dto.orgaccountadd.OrgAccountAddReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountdelete.OrgAccountDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountupdate.OrgAccountUpdateReq;

/**
 * 系统账户服务的接口
 * @author xxu1
 */
public interface AccountService {
    
    /**
     * 获取指定的系统账户的信息
     * @param bomanager
     * @param request
     * @return
     */
    OrgAccountQueryRsp getAccountInfo(OrgAccountQueryReq request);

    /**
     * 根据对应机构、账户类型、币种的获取系统账号信息
     * @param bomanager
     * @param request
     * @return
     */
    OrgAccountByOrgQueryRsp getAccountByOrg(OrgAccountByOrgQueryReq request);
    
    /**
     * 获取所有系统账户的信息
     * @param bomanager
     * @param request
     * @return
     */
    OrgAccountAllQueryRsp getAccountAll(OrgAccountAllQueryReq request);
    
    /**
     * 修改系统账户的信息
     * @param bomanager
     * @param request
     * @return
     */
    void setAccountInfo(OrgAccountUpdateReq request);

    /**
     * 新增系统账户的信息
     * @param bomanager
     * @param request
     * @return
     */
    void addAccountInfo(OrgAccountAddReq request);
    

    /**
     * 删除系统账户的信息
     * @param bomanager
     * @param request
     * @return
     */
    void deleteAccountInfo(OrgAccountDeleteReq request);
}
