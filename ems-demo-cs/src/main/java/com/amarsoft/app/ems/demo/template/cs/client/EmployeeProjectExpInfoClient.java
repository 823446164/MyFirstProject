package com.amarsoft.app.ems.demo.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.demo.template.controller.EmployeeProjectExpInfoController;

/**
 * 员工项目经历InfoFeign接口
 * @author jfan5
 */
@Component
@FeignClient(value = "demo-server")
@RequestMapping("/demo")
public interface EmployeeProjectExpInfoClient extends EmployeeProjectExpInfoController {
}
