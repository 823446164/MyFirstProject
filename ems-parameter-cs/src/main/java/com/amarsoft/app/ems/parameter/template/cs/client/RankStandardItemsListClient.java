/*
 * 文件名：RankStandardItemsListClient.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xphe
 * 修改时间：2020年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.cs.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.amarsoft.app.ems.parameter.template.controller.RankStandardItemsListController;

/**
 * 〈职级指标客户端〉
 * @author xphe
 * @version 2020年5月14日
 * @see RankStandardItemsListClient
 * @since
 */
@Component
@FeignClient(value = "parameter-server")
@RequestMapping("/parameter")
public interface RankStandardItemsListClient extends RankStandardItemsListController {
}
