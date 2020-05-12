/*
 * 文件名：LabelListClient
 * 版权：Copyright by www.amarsoft.com
 * 描述：LabelListController的继承类
 * 修改人：yrong
 * 修改时间：2020年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.parameter.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.parameter.template.controller.LabelListController;

/**
 * 标签ListFeign接口
 * @author yrong
 */
@Component
@FeignClient(value = "parameter-server")
@RequestMapping("/parameter")
public interface LabelListClient extends LabelListController {
}
