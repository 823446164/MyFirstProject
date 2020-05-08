package com.amarsoft.app.ems.employee.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.employee.template.controller.EmployeeTargetRankInfoDtoController;

/**
 * 目标职级申请InfoFeign接口
 * @author lding
 */
@Component
@FeignClient(value = "employee-server")
@RequestMapping("/employee")
public interface EmployeeTargetRankInfoDtoClient extends EmployeeTargetRankInfoDtoController {
}
