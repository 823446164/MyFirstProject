/*
 * 文件名：LabelCatalogTreeController.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcatalogtreequery.LabelCatalogTreeQueryRsp;

/**
 * 标签树图接口
 * @author yrong
 * @version 2020年5月13日
 * @see LabelCatalogTreeController
 * @since
 */

public interface LabelCatalogTreeController {   
    @PostMapping(value = "/labelcatalog/tree", name="标签目录树图展示功能接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<LabelCatalogTreeQueryRsp>> labelCatalogTreeQuery();
    
}
