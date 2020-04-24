package com.amarsoft.app.ems.system.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import com.amarsoft.amps.acsc.holder.GlobalShareContextHolder;
import com.amarsoft.aecd.common.constant.Status;
import com.amarsoft.aecd.system.constant.UserEventType;
import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arem.util.MessageHelper;
import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.entity.UserEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户事件工具类
 * @author hzhang23
 *
 */
@Slf4j
@Component
public class UserEventHelper{
    
    /**
     * 保存用户登录、退出等事件
     * @param event UserEventType枚举类中定义的事件类型
     * @param describe 事件说明
     * @param header httpHeader
     */
    
    public static void setUserEvent(String event,String describe,HttpHeaders header) {
        SpringHelper.getBean(UserEventHelper.class).setUserEvent(event, GlobalShareContextHolder.getUserId(), GlobalShareContextHolder.getOrgId(), DateHelper.getDateTime(DateHelper.getBusinessDateLongTime()), describe, 
                OSHelper.getOSType(header),BrowserHelper.getBrowserType(header), IPHelper.getIpAddr(header), Status.Valid.id);
    }
    
    /**
     * 记录登录成功用户事件
     * @param describe 事件说明
     * @param header httpHeader
     */
    public static void loginSuccess(String describe,HttpHeaders header) {
        SpringHelper.getBean(UserEventHelper.class).setUserEvent(UserEventType.LoginSuccess.id, GlobalShareContextHolder.getUserId(), GlobalShareContextHolder.getOrgId(), DateHelper.getDateTime(DateHelper.getBusinessDateLongTime()), describe, 
                OSHelper.getOSType(header),BrowserHelper.getBrowserType(header), IPHelper.getIpAddr(header), Status.Valid.id);
    }
    
    /**
     * 记录登出成功用户事件
     * @param describe 事件说明
     * @param header httpHeader
     */
    public static void logoutSuccess(String describe,HttpHeaders header) {
        SpringHelper.getBean(UserEventHelper.class).setUserEvent(UserEventType.LogoutSuccess.id, GlobalShareContextHolder.getUserId(), GlobalShareContextHolder.getOrgId(), DateHelper.getDateTime(DateHelper.getBusinessDateLongTime()), describe, 
                OSHelper.getOSType(header),BrowserHelper.getBrowserType(header), IPHelper.getIpAddr(header), Status.Valid.id);
    }
    
    /**
     * 保存用户登录、退出等事件
     * @param event UserEventType枚举类中定义的事件类型
     * @param userId 用户编号
     * @param orgId 机构编号
     * @param describe 事件说明
     * @param header httpHeader
     */
    public static void setUserEvent(String event,String userId,String orgId,String describe,HttpHeaders header) {
        SpringHelper.getBean(UserEventHelper.class).setUserEvent(event, userId, orgId, DateHelper.getDateTime(DateHelper.getBusinessDateLongTime()), describe, 
                OSHelper.getOSType(header),BrowserHelper.getBrowserType(header), IPHelper.getIpAddr(header), Status.Valid.id);
    }
    
    /**
     * 
     * @param event UserEventType枚举类中定义的事件类型
     * @param userId 用户编号
     * @param orgId 机构编号
     * @param eventTime 记录时间
     * @param describe 事件描述
     * @param os 操作系统
     * @param browswer 浏览器
     * @param ipAddress ip地址
     * @param status 状态
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setUserEvent(String event,String userId,String orgId,LocalDateTime eventTime,String describe,String os,String browswer,String ipAddress,String status) {
        try {
            UserEventHelper.setUserEvents(event, userId, orgId, eventTime, describe, os, browswer, ipAddress, status);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(MessageHelper.getMessage("901303"), e);
            }
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
    
    private static void setUserEvents(String event,String userId,String orgId,LocalDateTime eventTime,String describe,String os,String browswer,String ipAddress,String status) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        try {
            if(!UserEventType.isExist(event)) {
                throw new ALSException("901302") ;
            }
            UserEvent userEvent = new UserEvent();
            userEvent.generateKey();
            userEvent.setUserId(userId);
            userEvent.setOrgId(orgId);
            userEvent.setUserEventType(event);
            userEvent.setEventTime(eventTime);
            userEvent.setLog(describe);
            userEvent.setOs(os);
            userEvent.setBrowser(browswer);
            userEvent.setIpAddress(ipAddress);
            userEvent.setStatus(status);
            
            if (log.isDebugEnabled()) {
                log.debug("新增用户事件:" + userEvent.toString());
            }
            bomanager.updateBusinessObject(userEvent);
            bomanager.updateDB();
        } catch (Exception e) {
            throw new ALSException("901303",e.getMessage());
        }
    }
    
    /**
     * IP地址获取
     */
    static class IPHelper{
        private static final String LOCALHOST="127.0.0.1";
        private static final String UNKNOWN = "unknown";
        
        public static String getIpAddr(HttpHeaders header) {
            if (null == header) {
                return UNKNOWN;
            }

            if (CollectionUtils.isEmpty(header.get("x-forwarded-for"))) {
                return UNKNOWN;
            }
            if(header.get("x-forwarded-for").toString().equals("localhost")) {
                return LOCALHOST;
            }
            return header.get("x-forwarded-for").toString();
        }
    }
    
    /**
     * 获取操作系统
     */
    static class OSHelper{
        
        private static final String UNKNOWN = "unknown";
        private static final String OS_WINDOWS = "Windows";
        private static final String OS_MAC = "Mac";
        private static final String OS_LINUX = "Linux";
        private static final String OS_UNIX = "Unix";
        private static final String OS_ANDROID = "Android";
        private static final String OS_IPHONE = "Iphone";
        
        public static String getOSType(HttpHeaders header) {
            String os = "";
            if (null == header) {
                return os = UNKNOWN;
            }
            if (CollectionUtils.isEmpty(header.get("user-agent"))) {
                return os = UNKNOWN;
            }
            String userAgent = header.get("user-agent").toString();
            try {
                if (userAgent.toLowerCase().indexOf("windows") >= 0) {
                    os = OS_WINDOWS;
                } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
                    os = OS_MAC;
                } else if (userAgent.toLowerCase().indexOf("linux") >= 0) {
                    os = OS_LINUX;
                } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
                    os = OS_UNIX;
                } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
                    os = OS_ANDROID;
                } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
                    os = OS_IPHONE;
                } else {
                    os = UNKNOWN + ", More-Info: " + userAgent;
                } 
            } catch (Exception e) {
                return UNKNOWN + ", More-Info: " + userAgent;
            }
            return os;
        }
    }
    
    /**
     * 浏览器获取
     */
    static class BrowserHelper{
        
        private static final String UNKNOWN = "unknown";
        
        public static String getBrowserType(HttpHeaders header) {
            String browser = "";
            if (null == header) {
                return browser = UNKNOWN;
            }
            if (CollectionUtils.isEmpty(header.get("user-agent"))) {
                return browser = UNKNOWN;
            }
            String userAgent = header.get("user-agent").toString().toLowerCase();
            try {
                if (userAgent.contains("msie")) {
                    String substring=userAgent.substring(userAgent.indexOf("msie")).split(";")[0];
                    browser=substring.split(" ")[0].replace("msie", "IE")+"-"+substring.split(" ")[1];
                } else if (userAgent.contains("safari") && userAgent.contains("version")) { 
                    browser=(userAgent.substring(userAgent.indexOf("safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                 } else if ( userAgent.contains("opr") || userAgent.contains("opera")) { 
                     if(userAgent.contains("opera")) 
                         browser=(userAgent.substring(userAgent.indexOf("opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
                 else if(userAgent.contains("opr")) 
                     browser=((userAgent.substring(userAgent.indexOf("opr")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
                 } else if (userAgent.contains("chrome")) { 
                     browser=(userAgent.substring(userAgent.indexOf("chrome")).split(" ")[0]).replace("/", "-");
                 } else if ((userAgent.indexOf("mozilla/7.0") > -1) || (userAgent.indexOf("netscape6") != -1) || (userAgent.indexOf("mozilla/4.7") != -1) || (userAgent.indexOf("mozilla/4.78") != -1) || (userAgent.indexOf("mozilla/4.08") != -1) || (userAgent.indexOf("mozilla/3") != -1) ) { 
                     //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
                     browser = "netscape-?";
                 } else if (userAgent.contains("firefox")) { 
                     browser=(userAgent.substring(userAgent.indexOf("firefox")).split(" ")[0]).replace("/", "-");
                 } else if(userAgent.contains("rv")) { 
                     browser="IE-" + userAgent.substring(userAgent.indexOf("rv") + 3, userAgent.indexOf(")"));
                 } else { 
                     browser = UNKNOWN + ", More-Info: "+userAgent;
                 }
            } catch (Exception e) {
                return UNKNOWN + ", More-Info: " + userAgent;
            }
            return browser;
        }
    }
}
