package com.amarsoft.app.ems.train.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.train.template.controller.EmployeeJobTrainInfoController;

/**
 * 在职培训详情Feign接口
 * @author xphe
 */
@Component
@FeignClient(value = "train-server")
@RequestMapping("/train")
public interface EmployeeJobTrainInfoClient extends EmployeeJobTrainInfoController {
}
