package com.amarsoft.app.ems.system.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectCache;
import com.amarsoft.app.ems.system.cs.client.AccountClient;
import com.amarsoft.app.ems.system.cs.client.OrgClient;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccount;
import com.amarsoft.app.ems.system.cs.dto.orgaccountbyorgquery.OrgAccountByOrgQueryReq;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfo;
import com.amarsoft.app.ems.system.cs.dto.orginfoallquery.OrgInfoAllQueryReq;

import lombok.extern.slf4j.Slf4j;



/**
 * 机构、即其他信息缓存，可根据需要添加
 * 
 * @author hzhang23 
 */
@Slf4j
public final class OrgHelper{
    private static OrgClient orgClient = SpringHelper.getBean(OrgClient.class);
    private static AccountClient accountClient = SpringHelper.getBean(AccountClient.class);
    
    
    private static BusinessObjectCache orgCache = new BusinessObjectCache(50000);
    private static BusinessObjectCache orgAccountCache = new BusinessObjectCache(200000);
    
    private static final String ALL_ORGS = "ALL_ORGS";
    
    /**
     * 清理缓存
     */
    public static void clear()
    {
        orgCache = new BusinessObjectCache(10000);
        orgAccountCache = new BusinessObjectCache(10000);
    }
    
    /**
     * 获取状态正常的所有机构
     * @return 机构信息列表
     */
    public static List<OrgInfo> getOrgs() {
        List<OrgInfo> orgInfoList = getAllOrgs();
        List<OrgInfo> orgs = new ArrayList<>();
        for(OrgInfo orgInfo : orgInfoList) {
            if(SystemStatus.Normal.id.equals(orgInfo.getStatus())) {
                orgs.add(orgInfo);
            }
        }
        return orgs;
    }
    
    
    /**
     * 获取所有机构
     * @return 机构信息列表
     */
    public static List<OrgInfo> getAllOrgs() {
        List<OrgInfo> orgInfoList = (List<OrgInfo>)orgCache.getCacheObject(ALL_ORGS);
        if (CollectionUtils.isEmpty(orgInfoList)) {
            OrgInfoAllQueryReq request = new OrgInfoAllQueryReq();
            orgInfoList = orgClient.orgInfoAllQuery(new RequestMessage<OrgInfoAllQueryReq>(request)).getBody().getMessage().getOrgInfos();
            for(OrgInfo orgInfo : orgInfoList){
                orgCache.setCache(orgInfo.getOrgId(), orgInfo);
            }
            orgCache.setCache(ALL_ORGS, orgInfoList);
            return orgInfoList;
        }
        else 
            return orgInfoList;
    }
    
    /**
     * 获取指定机构项下的所有机构（含本机构）
     * @param orgId
     * @return 机构信息列表
     */
    public static List<OrgInfo> getOrgs(String orgId) {
        List<OrgInfo> orgInfoList = getOrgs();
        
        List<OrgInfo> orgs = new ArrayList<OrgInfo>();
        if(CollectionUtils.isEmpty(orgInfoList)) {
            return new ArrayList<OrgInfo>();
        }else {
            orgInfoList.forEach(orgInfo -> {
                if(orgInfo.getOrgId().equals(orgId)) {
                    orgs.add(orgInfo);
                }
                
                if(orgInfo.getParentOrgId().equals(orgId)) {//下级机构的下级机构
                    orgs.addAll(getOrgs(orgInfo.getOrgId()));
                }
            });
        }
        return orgs;
    }
    
    /**
     * 获取指定机构项下的指定机构级别的机构（含本机构）
     * @param orgId
     * @param orgLevel
     * @return 机构信息列表
     */
    public static List<OrgInfo> getOrgs(String orgId, String orgLevel) {
        List<OrgInfo> orgInfoList = getOrgs(orgId);
        
        List<OrgInfo> orgs = new ArrayList<OrgInfo>();
        if(CollectionUtils.isEmpty(orgInfoList)) {
            return new ArrayList<OrgInfo>();
        }else {
            orgInfoList.forEach(orgInfo -> {
                if(orgLevel.equals(orgInfo.getOrgLevel())) {
                    orgs.add(orgInfo);
                }
            });
        }
        return orgs;
    }
    
    
    /**
     * 获取指定机构的父机构，包含父机构的父机构，以此类推（含本机构）
     * @param orgId
     * @return 机构信息列表
     */
    public static List<OrgInfo> getParentOrgs(String orgId) {
        List<OrgInfo> orgInfoList = getOrgs();
        
        List<OrgInfo> orgs = new ArrayList<OrgInfo>();
        if(CollectionUtils.isEmpty(orgInfoList)) {
            return new ArrayList<OrgInfo>();
        }else {
            orgInfoList.forEach(orgInfo -> {
                if(orgInfo.getOrgId().equals(orgId)) {
                    orgs.add(orgInfo);
                    orgs.addAll(getParentOrgs(orgInfo.getParentOrgId()));//上级机构
                }
            });
        }
        return orgs;
    }
    
    /**
     * 获取指定机构的指定机构级别的父机构，包含父机构的父机构，以此类推（含本机构）
     * @param orgId
     * @param orgLevel
     * @return 机构信息列表
     */
    public static OrgInfo getParentOrg(String orgId, String orgLevel) {
        List<OrgInfo> orgInfoList = getParentOrgs(orgId);
        
        if(CollectionUtils.isEmpty(orgInfoList)) {
            return null;
        }else {
            for(OrgInfo orgInfo : orgInfoList) {
                if(orgInfo.getOrgLevel().equals(orgLevel)) {
                    return orgInfo;
                }
            }
        }
        return null;
    }
    
    /**
     * 获取指定机构ID的配置信息
     * @param orgId
     * @return 该机构信息
     */
    public static OrgInfo getOrg(String orgId) {
        OrgInfo orgInfo = (OrgInfo)orgCache.getCacheObject(orgId);
        if(orgInfo == null) {
            getAllOrgs();//如果缓存中机构不存在，就重新获取一下机构信息
            orgInfo = (OrgInfo)orgCache.getCacheObject(orgId);
        }
        
        if(orgInfo == null) {
            throw new ALSException("902022", orgId);
        }
        return orgInfo;
    }
    
    
    /**
     * 获取指定人行金融机构的机构信息
     * @param bankId
     * @return 机构信息列表
     */
    public static List<OrgInfo> getOrgsByBankId(String bankId) {
        List<OrgInfo> orgInfoList = getOrgs();
        
        List<OrgInfo> orgs = new ArrayList<OrgInfo>();
        if(CollectionUtils.isEmpty(orgInfoList)) {
            return new ArrayList<OrgInfo>();
        }else {
            orgInfoList.forEach(orgInfo -> {
                if(bankId.equals(orgInfo.getBankId())) {
                    orgs.add(orgInfo);
                }
            });
        }
        return orgs;
    }
    
    /**
     * 指定机构号获取机构名称
     * @param orgId
     * @return 机构名称
     */
    public static String getOrgName(String orgId) {
        String orgName = "";
        try {
            orgName = getOrg(orgId).getOrgName();
        } catch (Exception e) {
            if(log.isWarnEnabled()) {
                log.warn("未找到"+orgId+"的机构!");
            }
        }
        return orgName;
    }
    
    /**
     * 获取当前用户使用的机构名称
     * @return 机构名称
     */
    public static String getOrgName() {
        return getOrgName(GlobalShareContextHolder.getOrgId());
    }
    
    /**
     * 指定机构号获取机构级别
     * @param orgId
     * @return 机构级别
     */
    public static String getOrgLevel(String orgId) {
        return getOrg(orgId).getOrgLevel();
    }
    
    /**
     * 指定机构号获取机构类型
     * @param orgId
     * @return 机构类型
     */
    public static String getOrgType(String orgId) {
        return getOrg(orgId).getOrgType();
    }
    
    /**
     * 指定机构号获取上级机构号
     * @param orgId
     * @return 上级机构号
     */
    public static String getParentOrgId(String orgId) {
        return getOrg(orgId).getParentOrgId();
    }
    
    /**
     * 指定机构号获取法人机构号
     * @param orgId
     * @return 法人机构号
     */
    public static String getRootOrgId(String orgId) {
        return getOrg(orgId).getRootOrgId();
    }
    
    /**
     * 指定机构号获取所属分行机构号
     * @param orgId
     * @return 所属分行机构号
     */
    public static String getBranchOrgId(String orgId) {
        return getOrg(orgId).getBranchOrgId();
    }
    
    
    /**
     * 指定机构号获取所属核心机构号
     * @param orgId
     * @return 所属核心机构号
     */
    public static String getCoreOrgId(String orgId) {
        return getOrg(orgId).getCoreOrgId();
    }
    
    /**
     * 指定机构号获取该机构的数据权限机构
     * @param orgId
     * @return 权限机构号
     */
    public static String getAuthOrgId(String orgId) {
        OrgInfo org = getOrg(orgId);
        if(OrgType.Department.id.equals(org.getOrgType())) {//如果传入机构为部室，其上级机构为数据权限机构
            return org.getParentOrgId();
        }else {//如果传入机构不是部室直接用其机构号
            return org.getOrgId();
        }
    }
    

    /**
     * 取对应机构、账户类型、币种的内部账号信息，如果账号不存在则找该机构的上级机构账户信息
     * @param orgId
     * @param accountType
     * @param currency
     * @return 机构账户信息
     */
    public static OrgAccount getOrgAccount(String orgId, String accountType, String currency) {
        
        OrgAccount orgAccount = (OrgAccount)orgAccountCache.getCacheObject(orgId+"-"+accountType+"-"+currency);
        if(orgAccount!=null) return orgAccount;
        //设置请求参数
        OrgAccountByOrgQueryReq request = new OrgAccountByOrgQueryReq();
        request.setAccountCurrency(currency);
        request.setAccountOrgId(orgId);
        request.setAccountType(accountType);
        
        //获取系统账户信息
        List<OrgAccount> accountList = accountClient.orgAccountByOrgQuery(new RequestMessage<OrgAccountByOrgQueryReq>(request)).getBody().getMessage().getOrgAccounts();
        
        if(CollectionUtils.isEmpty(accountList)){
            String parentOrgId = getParentOrgId(orgId);
            if("root".equals(parentOrgId)) {//到法人机构还找不到账户，抛出异常
                throw new ALSException("902023",orgId ,accountType ,currency);
            }
            return getOrgAccount(parentOrgId, accountType, currency);
        }
        else{
            for(OrgAccount account : accountList) {
                if(SystemStatus.Normal.id.equals(account.getStatus())) {
                    orgAccountCache.setCache(orgId+"-"+accountType+"-"+currency, account);
                    return account;
                }
            }
        }
        throw new ALSException("902023",orgId ,accountType ,currency);
    }
    
    /**
     * 取对应机构、账户类型、币种的内部账户的属性信息，如果账号不存在则找该机构的上级机构账户的属性信息
     * @param orgId
     * @param accountType
     * @param currency
     * @param attribute
     * @return 机构账户的属性信息
     */
    public static Object getOrgAccount(String orgId, String accountType, String currency, String attribute) {
        OrgAccount orgAccount = OrgHelper.getOrgAccount(orgId, accountType, currency);
        List<Object> result = new ArrayList<>();
        ReflectionUtils.doWithFields(OrgAccount.class, field ->{
            ReflectionUtils.makeAccessible(field);
            result.add(ReflectionUtils.getField(field, orgAccount));
        }, field -> {
            return field.getName().equalsIgnoreCase(attribute);//不区分大小写
        });
        
        if(CollectionUtils.isEmpty(result)) {
            return null;
        }else {
            return result.get(0);
        }
    }
    
}

