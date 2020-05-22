/*文件名：EmployeeRankApplyInfoDtoController 
 * 版权：Copyright by www.amarsoft.com
 * 描述： 
 * 修改人：xphe 
 * 修改时间：2020/05/21
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：员工职级申请InfoController接口
 */

package com.amarsoft.app.ems.employee.template.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.acsc.rpc.ResponseMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateRsp;

/**
 * 〈员工职级申请InfoController接口〉
 * @author xphe
 * @version 2020年5月20日
 * @see 
 * @since
 */
public interface EmployeeRankApplyInfoDtoController {
    @PostMapping(value = "/employeerankapplyinfodto/query", name="员工职级申请Info查询接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<EmployeeRankApplyInfoDtoQueryRsp>> employeeRankApplyInfoDtoQuery(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoQueryReq> reqMsg);

    @PostMapping(value = "/employeerankapplyinfodto/save", name="员工职级申请Info保存接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<Object>> employeeRankApplyInfoDtoSave(@RequestBody @Valid RequestMessage<EmployeeRankApplyInfoDtoSaveReq> reqMsg);

    @PostMapping(value = "/employeerankapplyinfodto/deletevalidate", name="子职级删除校验接口", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage<RankDeleteValidateRsp>> employeeRankApplyInfoExist(@RequestBody @Valid RequestMessage<RankDeleteValidateReq> reqMsg);
}
