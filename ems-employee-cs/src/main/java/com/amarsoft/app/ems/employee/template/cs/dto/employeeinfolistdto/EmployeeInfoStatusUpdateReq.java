/*文件名：EmployeeInfoStatusUpdateReq
 * 版权：Copyright by www.amarsoft.com
 * 描述：员工状态请求体
 * 修改人：dxiao
 * 修改时间：2020/05/20
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.employee.template.cs.dto.employeeinfolistdto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.employee.template.cs.dto.employeeinfodto.EmployeeInfoDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * 员工信息Info 单字段员工状态更新
 * @author jxzhou1
 */
@Getter
@Setter
@ToString
public class EmployeeInfoStatusUpdateReq extends EmployeeInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;
    @Description("员工编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EI.employeeNo")
    private String employeeNo;
    
    @Description("员工状态")
    @Length(max=10)
    @ActualColumn("EI.employeeStatus")
    private String employeeStatus;

}
