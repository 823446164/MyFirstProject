package com.amarsoft.app.ems.parameter.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.parameter.template.controller.LabelInfoController;

/**
 * 标签InfoFeign接口
 * @author ylgao
 */
@Component
@FeignClient(value = "parameter-server")
@RequestMapping("/parameter")
public interface LabelInfoClient extends LabelInfoController {
}
