/* 文件名：EmployeeProjectExpInfoDtoSaveReq
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/15
 * 跟踪单号：
 * 修改单号：
 * 修改内容：添加字段
 */
package com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexpinfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工项目经历Info保存请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeProjectExpInfoDtoSaveReq extends EmployeeProjectExpInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("项目编号")
    @NotEmpty
    @ActualColumn("EPE.serialNo")
    private String serialNo;
    
    @Description("工作描述")
    @NotEmpty
    @ActualColumn("EPE.workDescribe")
    private String workDescribe;
    
}
