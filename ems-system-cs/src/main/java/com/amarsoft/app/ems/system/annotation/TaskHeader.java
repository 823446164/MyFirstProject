package com.amarsoft.app.ems.system.annotation;

import java.lang.annotation.*;

/**
 * @Description 代办任务模板定义
 * @Author dchen1
 * @Date 2019/9/29 下午4:09
 * @Version
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskHeader {
    String id(); // 模板编号
    String name(); // 模板名称
}
