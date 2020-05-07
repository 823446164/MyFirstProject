package com.amarsoft.app.ems.parameter.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.parameter.template.controller.LableCatalogListController;

/**
 * 标签目录树图Feign接口
 * @author ylgao
 */
@Component
@FeignClient(value = "parameter-server")
@RequestMapping("/parameter")
public interface LableCatalogListClient extends LableCatalogListController {
}
