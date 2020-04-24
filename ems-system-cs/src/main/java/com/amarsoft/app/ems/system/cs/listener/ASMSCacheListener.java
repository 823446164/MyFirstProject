package com.amarsoft.app.ems.system.cs.listener;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.amarsoft.amps.mscr.event.RefreshCacheApplicationEvent;
import com.amarsoft.app.ems.system.util.CalendarHelper;
import com.amarsoft.app.ems.system.util.ERateHelper;
import com.amarsoft.app.ems.system.util.GroupHelper;
import com.amarsoft.app.ems.system.util.OrgHelper;
import com.amarsoft.app.ems.system.util.RateHelper;
import com.amarsoft.app.ems.system.util.RoleHelper;
import com.amarsoft.app.ems.system.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * 根据Spring容器事件刷新业务缓存
 * @author xjzhao
 *
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "spring.cloud.config.enabled", havingValue = "true")
public class ASMSCacheListener implements ApplicationListener<RefreshCacheApplicationEvent> {

    /**
     * 这里写了一个公共的缓存刷新类，各个应用可根据需要继承该类重写该方法。
     */
    @Override
    public void onApplicationEvent(RefreshCacheApplicationEvent event) {
        long begin = System.nanoTime();
        if(log.isInfoEnabled()) {
            log.info("系统管理缓存清理开始..........................................");
        }
        CalendarHelper.clear();//日历工作日缓存
        ERateHelper.clear();//汇率缓存
        GroupHelper.clear();//角色组缓存
        OrgHelper.clear();//机构缓存
        RateHelper.clear();//利率缓存
        RoleHelper.clear();//角色缓存
        UserHelper.clear();//用户缓存
        long end = System.nanoTime();
        if(log.isInfoEnabled()) {
            log.info("系统管理缓存清理完成..........................................共耗时【"+(end-begin)+"】纳秒");
        }
    }
}
