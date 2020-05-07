package com.amarsoft.app.ems.project.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.project.template.controller.ProjectEmployeeChangeController;

/**
 * 项目组人员变更信息Feign接口
 * @author hpli
 */
@Component
@FeignClient(value = "project-server")
@RequestMapping("/project")
public interface ProjectEmployeeChangeClient extends ProjectEmployeeChangeController {
}
