/*
 * 文件名：EmployeeRankRelabelListDtoServiceImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：职级标签实现类
 * 修改人：dxiao
 * 修改时间：2020年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.employee.template.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDto;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto.EmployeeRankRelabelListDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankRelabelListDtoService;
import com.amarsoft.app.ems.parameter.template.cs.client.LabelCatalogInfoClient;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryReq;
import com.amarsoft.app.ems.parameter.template.cs.dto.labelcataloginfo.LabelCatalogInfoQueryRsp;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dxiao
 * @version 2020年5月19日
 * @see EmployeeRankRelabelListDtoServiceImpl
 * @since
 */
@Slf4j
@Service
public class EmployeeRankRelabelListDtoServiceImpl implements EmployeeRankRelabelListDtoService{


    @Autowired
    LabelCatalogInfoClient labelCatalogInfoClient;
    /**
     * 目标职级申请List多记录查询
     * 
     * @param request
     * @return
     */
    @Override
    @Transactional
    public EmployeeRankRelabelListDtoQueryRsp employeeRankRelabelListDtoQuery(@Valid EmployeeRankRelabelListDtoQueryReq employeeRankRelabelListDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        // 1.根据点击职级编号,查询标签所在的流水号,和对应的掌握程度
        List<BusinessObject> labelCatalogs =
          bomanager.selectBusinessObjectsBySql(  "select ERR.serialNo as serialNo,ERR.rankNo as rankNo,ERR.labelNo as labelNo,ERR.level as level,ERR.belongCatalog as belongCatalog "
              + " from EmployeeRankRelabel ERR"
              + " where 1=1 and ERR.rankNo = :rankNo",
            "rankNo",employeeRankRelabelListDtoQueryReq.getRankNo()).getBusinessObjects();
        //职级标签响应体对象
        EmployeeRankRelabelListDtoQueryRsp response = new EmployeeRankRelabelListDtoQueryRsp();
        
        List<EmployeeRankRelabelListDto> employeeRankRelabelListDtos = null;        
        if (!CollectionUtils.isEmpty(labelCatalogs)) {//集合判空
            // 2.循环输出List
            employeeRankRelabelListDtos = new ArrayList<EmployeeRankRelabelListDto>();
            for (BusinessObject bo : labelCatalogs) {
                //新建标签对象
                EmployeeRankRelabelListDto temp = new EmployeeRankRelabelListDto();
                //调用查询标签名称的接口
                //1.获取请求头对象
                //2.新建对象
                //3.设置请求字段
                //4.获取查询对象,拿到标签名称
                RequestMessage<LabelCatalogInfoQueryReq> reqMsg = new RequestMessage<LabelCatalogInfoQueryReq>();
                LabelCatalogInfoQueryReq req = new LabelCatalogInfoQueryReq();
                req.setSerialNo(bo.getString("LabelNo"));
                reqMsg.setMessage(req);              
                ResponseEntity<ResponseMessage<LabelCatalogInfoQueryRsp>> labelCatalogInfoQuery = labelCatalogInfoClient.labelCatalogInfoQuery(reqMsg);
                String labelName = "";
                if (!StringUtils.isEmpty(labelCatalogInfoQuery)) {
                    labelName = labelCatalogInfoQuery.getBody().getMessage().getLabelName();
                }               
                //查询到的数据转换为响应实体
                temp.setSerialNo(bo.getString("SerialNo"));
                temp.setRankNo(bo.getString("RankNo"));
                temp.setLabelNo(bo.getString("LabelNo"));
                temp.setLabelName(labelName);
                temp.setLevel(bo.getString("Level"));
                temp.setBelongCatalog(bo.getString("BelongCatalog"));
                employeeRankRelabelListDtos.add(temp);
            }
        }
        response.setEmployeeRankRelabelListDto(employeeRankRelabelListDtos);
        //总条数
        response.setTotalCount(response.getEmployeeRankRelabelListDto().size());
        
        return response;
    }
    

}
