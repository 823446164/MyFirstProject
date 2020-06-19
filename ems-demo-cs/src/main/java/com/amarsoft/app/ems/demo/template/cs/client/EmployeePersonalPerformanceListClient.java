package com.amarsoft.app.ems.demo.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.demo.template.controller.EmployeePersonalPerformanceListController;

/**
 * 员工项目经历个人表现ListFeign接口
 * @author jfan5
 */
@Component
@FeignClient(value = "demo-server")
@RequestMapping("/demo")
public interface EmployeePersonalPerformanceListClient extends EmployeePersonalPerformanceListController {
}
