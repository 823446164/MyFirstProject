package com.amarsoft.app.ems.system.cache;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.avts.dto.GetTemplateReq;
import com.amarsoft.app.ems.system.annotation.TaskBody;
import com.amarsoft.app.ems.system.annotation.TaskHeader;
import com.amarsoft.app.ems.system.cs.dto.tasktemplate.TaskTab;
import com.amarsoft.app.ems.system.cs.dto.tasktemplate.TaskTemplateRsp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 代办任务模板
 * @Author dchen1
 * @Date 2019/9/29 下午5:22
 * @Version
 */
@Slf4j
public class TaskTemplateCache {
    /**
     * 模板id与模板头注解的关联关系
     */
    private final static Map<String, Class<?>> template = new ConcurrentHashMap<>();

    /**
     * 获取指定模板类定义
     *
     * @param templateId
     * @return
     */
    public final static Class<?> getClass(String templateId) {
        return template.get(templateId);
    }

    /**
     * 指定模板类定义
     *
     * @param templateId
     * @param c
     * @return
     */
    public final static void setClass(String templateId, Class<?> c) {
        template.put(templateId, c);
    }

    /**
     * 组装代办模板定义
     *
     * @param templateId 模板编号
     * @return
     */
    public final static TaskTemplateRsp getTemplate(String templateId) {
        Class<?> taskCls = getClass(templateId);
        if (taskCls == null) {
            return null;
        }
        TaskHeader th = taskCls.getAnnotation(TaskHeader.class);
        if (th == null) {
            return null;
        }

        TaskTemplateRsp rsp = new TaskTemplateRsp();
        rsp.setId(th.id());
        rsp.setName(th.name());
        rsp.setTaskTabs(getTaskTabs(taskCls));
        return rsp;
    }


    private final static List<TaskTab> getTaskTabs(Class<?> c) {
        List<TaskTab> taskTabs = new ArrayList<>();
        ReflectionUtils.doWithFields(c, field -> {
                    ReflectionUtils.makeAccessible(field);
                    Description description = field.getAnnotation(Description.class);
                    TaskBody taskBody = field.getAnnotation(TaskBody.class);

                    TaskTab taskTab = new TaskTab();
                    taskTab.setTitle(description.value()==null?"":description.value());
                    taskTab.setIcon(taskBody.icon());
                    taskTab.setSortNo(taskBody.sortNo());
                    taskTab.setBgColor(taskBody.bgColor());
                    taskTab.setSuffix(taskBody.suffix());
                    taskTab.setServiceName(taskBody.serviceName());
                    taskTab.setObjectType(field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
                    taskTab.setDealUrl(taskBody.dealUrl());
                    taskTab.setDealParam(taskBody.dealParam());
                    GetTemplateReq getTemplateReq = new GetTemplateReq();
                    getTemplateReq.setTemplateId(taskBody.templateId());
                    taskTab.setTemplateId(taskBody.templateId());
                    taskTabs.add(taskTab);

                }, field -> field.getAnnotation(TaskBody.class) != null
        );
        return taskTabs;
    }

}
