package com.amarsoft.app.ems.system.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.system.template.controller.RoleInfoDtoController;

/**
 * 角色信息InfoFeign接口
 * @author cmhuang
 */
@Component
@FeignClient(value = "asms-server")
@RequestMapping("/asms")
public interface RoleInfoDtoClient extends RoleInfoDtoController {
}
