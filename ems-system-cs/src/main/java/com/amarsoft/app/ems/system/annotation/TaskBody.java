package com.amarsoft.app.ems.system.annotation;

import java.lang.annotation.*;

/**
 * @Description 代办任务模板字段定义
 * @Author dchen1
 * @Date 2019/9/29 下午4:09
 * @Version
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskBody {
    String icon(); // 图标
    String bgColor() default ""; // 背景颜色
    int sortNo() default 0; // 排序号
    String suffix() default ""; // 展示后缀
    String templateId(); // 对应展示模板
    String serviceName(); // 目标服务名
    String dealUrl() default "";; // 处理的url
    String dealParam() default "";; // 处理需要的参数
}
