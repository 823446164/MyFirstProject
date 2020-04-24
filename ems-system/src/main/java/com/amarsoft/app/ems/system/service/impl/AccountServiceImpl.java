package com.amarsoft.app.ems.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.system.cs.dto.orgaccountadd.OrgAccountAddReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccountAllQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountdelete.OrgAccountDeleteReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orgaccountquery.OrgAccountQueryRsp;
import com.amarsoft.app.ems.system.cs.dto.orgaccountupdate.OrgAccountUpdateReq;
import com.amarsoft.app.ems.system.entity.OrgAccount;
import com.amarsoft.app.ems.system.service.AccountService;

/**
 * 机构账户服务的接口实现类
 * @author xxu1
 */
@Service
public class AccountServiceImpl implements AccountService {
    
    @Override
    public OrgAccountQueryRsp getAccountInfo(OrgAccountQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgAccount account = bomanager.keyLoadBusinessObject(OrgAccount.class, request.getSerialNo());
        
        if(account == null) return null;
        
        OrgAccountQueryRsp response = new OrgAccountQueryRsp();
        
        //设置响应报文的参数
        response.setSerialNo(account.getSerialNo());
        response.setAccountCurrency(account.getAccountCurrency());
        response.setAccountName(account.getAccountName());
        response.setAccountNo(account.getAccountNo());
        response.setAccountOrgId(account.getAccountOrgId());
        response.setAccountType(account.getAccountType());
        response.setStatus(account.getStatus());
        return response;
    }

    @Override
    public OrgAccountByOrgQueryRsp getAccountByOrg(OrgAccountByOrgQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<OrgAccount> orgAccountList = bomanager.loadBusinessObjects(OrgAccount.class, 0, Integer.MAX_VALUE, 
                        "AccountOrgId=:AccountOrgId and AccountType=:AccountType and AccountCurrency=:AccountCurrency",
                        "AccountOrgId", request.getAccountOrgId(), "AccountType", request.getAccountType(),
                        "AccountCurrency", request.getAccountCurrency()).getBusinessObjects();
        OrgAccountByOrgQueryRsp response = new OrgAccountByOrgQueryRsp();
        List<com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccount> orgAccounts = new ArrayList<com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccount>();
        response.setOrgAccounts(orgAccounts);
        if(CollectionUtils.isEmpty(orgAccountList)) return response;
        for(OrgAccount account : orgAccountList) {
            com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccount accountResponse = new com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccount();
            
            //设置响应报文的参数
            accountResponse.setSerialNo(account.getSerialNo());
            accountResponse.setAccountCurrency(account.getAccountCurrency());
            accountResponse.setAccountName(account.getAccountName());
            accountResponse.setAccountNo(account.getAccountNo());
            accountResponse.setAccountOrgId(account.getAccountOrgId());
            accountResponse.setAccountType(account.getAccountType());
            accountResponse.setStatus(account.getStatus());
            
            orgAccounts.add(accountResponse);
        }
        response.setOrgAccounts(orgAccounts);
        return response;
    }
    
    @Override
    public OrgAccountAllQueryRsp getAccountAll(OrgAccountAllQueryReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        List<OrgAccount> orgAccountList = bomanager.loadBusinessObjects(OrgAccount.class, 0, Integer.MAX_VALUE, "Status=:Status", "Status", request.getStatus()).getBusinessObjects();
        OrgAccountAllQueryRsp response = new OrgAccountAllQueryRsp();
        List<com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccount> orgAccounts = null;
        if(!CollectionUtils.isEmpty(orgAccountList)) {
            orgAccounts = new ArrayList<com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccount>();
            for(OrgAccount account : orgAccountList) {
                com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccount accountResponse = new com.amarsoft.app.ems.system.cs.dto.orgaccountallquery.OrgAccount();
                
                //设置响应报文的参数
                accountResponse.setSerialNo(account.getSerialNo());
                accountResponse.setAccountCurrency(account.getAccountCurrency());
                accountResponse.setAccountName(account.getAccountName());
                accountResponse.setAccountNo(account.getAccountNo());
                accountResponse.setAccountOrgId(account.getAccountOrgId());
                accountResponse.setAccountType(account.getAccountType());
                accountResponse.setStatus(account.getStatus());
                orgAccounts.add(accountResponse);
            }
        } 
        response.setOrgAccounts(orgAccounts);
        return response;
    }

    @Override
    public void setAccountInfo(OrgAccountUpdateReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgAccount orgAccount = bomanager.keyLoadBusinessObject(OrgAccount.class, request.getSerialNo());
        
        if(orgAccount == null) orgAccount = new OrgAccount();//不存在就新增机构账户
        
        orgAccount.setSerialNo(request.getSerialNo());
        orgAccount.setAccountCurrency(request.getAccountCurrency());
        orgAccount.setAccountName(request.getAccountName());
        orgAccount.setAccountNo(request.getAccountNo());
        orgAccount.setAccountOrgId(request.getAccountOrgId());
        orgAccount.setAccountType(request.getAccountType());
        orgAccount.setStatus(request.getStatus());
        
        //更新到数据库
        bomanager.updateBusinessObject(orgAccount);
        bomanager.updateDB();
    }

    @Override
    public void addAccountInfo(OrgAccountAddReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgAccount orgAccount = new OrgAccount();

        orgAccount.setSerialNo(request.getSerialNo());
        orgAccount.setAccountCurrency(request.getAccountCurrency());
        orgAccount.setAccountName(request.getAccountName());
        orgAccount.setAccountNo(request.getAccountNo());
        orgAccount.setAccountOrgId(request.getAccountOrgId());
        orgAccount.setAccountType(request.getAccountType());
        orgAccount.setStatus(Status.Valid.id);
        
        //更新到数据库
        bomanager.updateBusinessObject(orgAccount);
        bomanager.updateDB();
    }

    @Override
    public void deleteAccountInfo(OrgAccountDeleteReq request) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        OrgAccount orgAccount = bomanager.keyLoadBusinessObject(OrgAccount.class, request.getSerialNo());
        if(orgAccount == null) 
            throw new ALSException("900306");
        bomanager.deleteBusinessObject(orgAccount);
        bomanager.updateDB();
    }    
}
