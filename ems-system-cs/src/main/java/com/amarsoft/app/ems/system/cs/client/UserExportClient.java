/**
 * 更新用户导出记录
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amarsoft.app.ems.system.controller.UserExportController;

@Component
@FeignClient(value = "asms-server")
@RequestMapping("/asms")
public interface UserExportClient extends UserExportController {
}