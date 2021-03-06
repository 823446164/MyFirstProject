package com.amarsoft.app.ems.project.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.project.template.controller.ProjectInfoController;

/**
 * 项目列表Feign接口
 * @author hpli
 */
@Component
@FeignClient(value = "project-server")
@RequestMapping("/project")
public interface ProjectInfoClient extends ProjectInfoController {
}
