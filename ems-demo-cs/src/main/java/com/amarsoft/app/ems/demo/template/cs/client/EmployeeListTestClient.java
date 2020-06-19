package com.amarsoft.app.ems.demo.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.demo.template.controller.EmployeeListTestController;

/**
 * 员工信息ListFeign接口
 * @author jfan5
 */
@Component
@FeignClient(value = "demo-server")
@RequestMapping("/demo")
public interface EmployeeListTestClient extends EmployeeListTestController {
}
