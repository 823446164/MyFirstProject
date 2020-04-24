package com.amarsoft.app.ems.system.listener;

import com.amarsoft.amps.arem.util.SpringHelper;
import com.amarsoft.amps.mscr.event.CacheApplicationEvent;
import com.amarsoft.app.ems.system.annotation.TaskHeader;
import com.amarsoft.app.ems.system.cache.TaskTemplateCache;

import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Description 代办任务模板配置信息的扫描
 * @Author dchen1
 * @Date 2019/9/29 下午4:38
 * @Version
 */
@Component
public class TaskTemplateScannerListener implements ApplicationListener<CacheApplicationEvent> {
    @Override
    public void onApplicationEvent(CacheApplicationEvent event) {
        try {
            Set<Class<?>> taskClss = new EntityScanner(SpringHelper.getApplicationContext()).scan(TaskHeader.class);
            taskClss.stream().forEach(c->{
                TaskHeader th = c.getAnnotation(TaskHeader.class);
                TaskTemplateCache.setClass(th.id(),c);
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
