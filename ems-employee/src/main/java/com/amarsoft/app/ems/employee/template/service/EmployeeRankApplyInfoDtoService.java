/*文件名：EmployeeRankApplyInfoDtoService 
 * 版权：Copyright by www.amarsoft.com
 * 描述： 
 * 修改人：xphe 
 * 修改时间：2020/05/21
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：新增职级删除/指标选择校验
 */

package com.amarsoft.app.ems.employee.template.service;

import javax.validation.Valid;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateRsp;

/**
 * 〈员工职级申请InfoService接口〉
 * 
 * @author xphe
 * @version 2020年5月20日
 * @see 
 * @since
 */
public interface EmployeeRankApplyInfoDtoService {

    /**
     * 
     * Description: 员工职级申请Info查询
     * @param employeeRankApplyInfoDtoQueryReq
     * @return
     * @see
     */
    public EmployeeRankApplyInfoDtoQueryRsp employeeRankApplyInfoDtoQuery(@Valid EmployeeRankApplyInfoDtoQueryReq employeeRankApplyInfoDtoQueryReq);

    /**
     * 
     * Description: 员工职级申请Info保存
     * @param employeeRankApplyInfoDtoSaveReq
     * @return
     * @see
     */
    public void employeeRankApplyInfoDtoSave(@Valid EmployeeRankApplyInfoDtoSaveReq employeeRankApplyInfoDtoSaveReq);
    
    /**
     * 
     * Description: 职级删除/指标选择校验
     * @param rankDeleteValidateReq
     * @return
     * @see
     */
    public RankDeleteValidateRsp employeeRankApplyInfoExist(@Valid RankDeleteValidateReq rankDeleteValidateReq);
}
