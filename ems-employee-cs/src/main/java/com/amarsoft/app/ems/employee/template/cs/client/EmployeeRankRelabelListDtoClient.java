/*
 * 文件名：EmployeeRankRelabelListDtoClient.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */

package com.amarsoft.app.ems.employee.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amarsoft.app.ems.employee.template.controller.EmployeeRankRelabelListDtoController;

/**
 * @author dxiao
 * @version 2020年5月19日
 * @see EmployeeRankRelabelListDtoClient
 * @since
 */
@Component
@FeignClient(value = "employee-server")
@RequestMapping("/employee")
public interface EmployeeRankRelabelListDtoClient extends EmployeeRankRelabelListDtoController{

}
