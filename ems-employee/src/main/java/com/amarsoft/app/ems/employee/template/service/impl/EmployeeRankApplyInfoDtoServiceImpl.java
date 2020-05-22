/*文件名：EmployeeRankApplyInfoDtoServiceImpl 
 * 版权：Copyright by www.amarsoft.com
 * 描述： 
 * 修改人：xphe 
 * 修改时间：2020/05/21
 * 跟踪单号： 
 * 修改单号： 
 * 修改内容：新增职级删除/指标选择校验
 */

package com.amarsoft.app.ems.employee.template.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.amarsoft.amps.arpe.businessobject.BusinessObject;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager.BusinessObjectAggregate;
import com.amarsoft.app.ems.employee.template.service.EmployeeRankApplyInfoDtoService;
import com.amarsoft.app.ems.employee.entity.EmployeeRankApply;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoQueryReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDtoSaveReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateReq;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.RankDeleteValidateRsp;
import com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplyinfodto.EmployeeRankApplyInfoDto;

/**
 * 〈员工职级申请InfoService实现类〉
 * 
 * @author xphe
 * @version 2020年5月20日
 * @see 
 * @since
 */
@Slf4j
@Service
public class EmployeeRankApplyInfoDtoServiceImpl implements EmployeeRankApplyInfoDtoService{
    
    /**
     * 
     * Description: 员工职级申请Info查询
     * @param employeeRankApplyInfoDtoQueryReq
     * @return
     * @see
     */
    @Override
    @Transactional
    public EmployeeRankApplyInfoDtoQueryRsp employeeRankApplyInfoDtoQuery(@Valid EmployeeRankApplyInfoDtoQueryReq employeeRankApplyInfoDtoQueryReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        
        EmployeeRankApply employeeRankApply = bomanager.loadBusinessObject(EmployeeRankApply.class,"serialNo",employeeRankApplyInfoDtoQueryReq.getSerialNo());
        if(employeeRankApply!=null){
            EmployeeRankApplyInfoDtoQueryRsp employeeRankApplyInfoDto = new EmployeeRankApplyInfoDtoQueryRsp();
            employeeRankApplyInfoDto.setSerialNo(employeeRankApply.getSerialNo());
            employeeRankApplyInfoDto.setRankNo(employeeRankApply.getRankNo());
            employeeRankApplyInfoDto.setEmployeeNo(employeeRankApply.getEmployeeNo());
            employeeRankApplyInfoDto.setEmployeeName(employeeRankApply.getEmployeeName());
            employeeRankApplyInfoDto.setRankName(employeeRankApply.getRankName());
            employeeRankApplyInfoDto.setUpdateRankName(employeeRankApply.getUpdateRankName());
            employeeRankApplyInfoDto.setTeamManager(employeeRankApply.getTeamManager());
            employeeRankApplyInfoDto.setChangePerson(employeeRankApply.getChangePerson());
            employeeRankApplyInfoDto.setBeginTime(employeeRankApply.getBeginTime());
            employeeRankApplyInfoDto.setChangeReason(employeeRankApply.getChangeReason());
            employeeRankApplyInfoDto.setInputUserId(employeeRankApply.getInputUserId());
            employeeRankApplyInfoDto.setInputTime(employeeRankApply.getInputTime());
            employeeRankApplyInfoDto.setInputOrgId(employeeRankApply.getInputOrgId());
            employeeRankApplyInfoDto.setUpdateUserId(employeeRankApply.getUpdateUserId());
            employeeRankApplyInfoDto.setUpdateTime(employeeRankApply.getUpdateTime());
            employeeRankApplyInfoDto.setUpdateOrgId(employeeRankApply.getUpdateOrgId());
            return employeeRankApplyInfoDto;
        }

        return null;
    }

    /**
     * 
     * Description: 员工职级申请Info单记录保存
     * @param employeeRankApplyInfoDtoSaveReq
     * @return
     * @see
     */
    @Override
    public void employeeRankApplyInfoDtoSave(@Valid EmployeeRankApplyInfoDtoSaveReq employeeRankApplyInfoDtoSaveReq) {
        employeeRankApplyInfoDtoSaveAction(employeeRankApplyInfoDtoSaveReq);
    }

    /**
     * 
     * Description: 员工职级申请Info单记录保存动作
     * @param employeeRankApplyInfoDto
     * @return
     * @see
     */
    @Transactional
    public void employeeRankApplyInfoDtoSaveAction(EmployeeRankApplyInfoDto employeeRankApplyInfoDto){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        if(employeeRankApplyInfoDto!=null){
        }
        bomanager.updateDB();
    }

    /**
     * 
     * Description: 子职级的删除/修改/增加标签的校验
     * @param rankDeleteValidateReq
     * @return
     * @see
     */
    @Override
    @Transactional
    public RankDeleteValidateRsp employeeRankApplyInfoExist(@Valid RankDeleteValidateReq rankDeleteValidateReq) {
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        RankDeleteValidateRsp ranDeleteValidateRsp=null;
        if(!StringUtils.isEmpty(rankDeleteValidateReq.getRankNo())) {
            ranDeleteValidateRsp=new RankDeleteValidateRsp();
            //子职级校验
            BusinessObjectAggregate<BusinessObject> count = bomanager.selectBusinessObjectsBySql(
                "select count(1) as cnt from EmployeeRankApply er  where" + " ( date_format(er.inputTime,'%Y%m')=date_format(NOW(),'%Y%m')  and er.approveStatus in ('1','2') and er.rankNo=:rankNo)","rankNo",rankDeleteValidateReq.getRankNo());
            ranDeleteValidateRsp.setRecordCount(count.getBusinessObjects().get(0).getInt("cnt"));
        }
        return ranDeleteValidateRsp;      
    }

}
