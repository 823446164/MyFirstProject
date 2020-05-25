/*
 * 文件名：PowerController.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：判断当前用户是否有权限对标签进行维护
 * 修改人：yrong
 * 修改时间：2020年5月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.parameter.template.cs.dto.powertolabel.PowerToLableQueryRsp;

/**
 * 判断当前用户是否有权限对标签进行维护
 * 返回true则代表有权限，返回false代表无权限
 * @author yrong
 * @version 2020年5月22日
 * @see PowerController
 * @since
 */

public interface PowerController {
    @PostMapping(value = "/labelcatalog/powercontrol", name="当前用户对标签维护权限判断功能接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<PowerToLableQueryRsp>> powerToLabel();
}
