/*
 * 文件名：TeamClient.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amarsoft.app.ems.system.controller.TeamController;;

/**
 * 〈团队信息客户端〉
 * @author xphe
 * @version 2020年5月9日
 * @see TeamClient
 * @since
 */
@Component
@FeignClient(value = "asms-server")
@RequestMapping("/asms")
public interface TeamClient extends TeamController{

}
