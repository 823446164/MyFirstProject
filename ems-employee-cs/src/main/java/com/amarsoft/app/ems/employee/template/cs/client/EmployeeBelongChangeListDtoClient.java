package com.amarsoft.app.ems.employee.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.employee.template.controller.EmployeeBelongChangeListDtoController;

/**
 * 团队调整申请ListFeign接口
 * @author lding
 */
@Component
@FeignClient(value = "employee-server")
@RequestMapping("/employee")
public interface EmployeeBelongChangeListDtoClient extends EmployeeBelongChangeListDtoController {
}
