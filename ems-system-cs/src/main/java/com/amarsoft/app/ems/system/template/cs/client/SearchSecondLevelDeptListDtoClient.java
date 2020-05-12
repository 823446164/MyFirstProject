package com.amarsoft.app.ems.system.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.system.template.controller.SearchSecondLevelDeptListDtoController;

/**
 * 搜索二级部门信息ListFeign接口
 * @author zcluo
 */
@Component
@FeignClient(value = "system-server")
@RequestMapping("/system")
public interface SearchSecondLevelDeptListDtoClient extends SearchSecondLevelDeptListDtoController {
}
