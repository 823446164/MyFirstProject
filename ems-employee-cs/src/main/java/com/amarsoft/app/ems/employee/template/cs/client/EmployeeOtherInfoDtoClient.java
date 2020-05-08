package com.amarsoft.app.ems.employee.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.employee.template.controller.EmployeeOtherInfoDtoController;

/**
 * 员工其他说明InfoFeign接口
 * @author lding
 */
@Component
@FeignClient(value = "employee-server")
@RequestMapping("/employee")
public interface EmployeeOtherInfoDtoClient extends EmployeeOtherInfoDtoController {
}
