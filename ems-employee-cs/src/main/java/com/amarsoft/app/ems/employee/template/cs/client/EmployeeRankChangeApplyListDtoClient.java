package com.amarsoft.app.ems.employee.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.employee.template.controller.EmployeeRankChangeApplyListDtoController;

/**
 * 员工职级调整申请ListFeign接口
 * @author xucheng
 */
@Component
@FeignClient(value = "employee-server")
@RequestMapping("/employee")
public interface EmployeeRankChangeApplyListDtoClient extends EmployeeRankChangeApplyListDtoController {
}
