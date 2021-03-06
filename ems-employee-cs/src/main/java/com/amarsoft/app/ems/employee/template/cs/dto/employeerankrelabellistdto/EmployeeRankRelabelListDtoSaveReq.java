/*文件名：EmployeeRankRelabelListDtoSaveReq
 * 版权：Copyright by www.amarsoft.com
 * 描述：职级标签保存请求体
 * 修改人：dxiao
 * 修改时间：2020/05/19
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */
package com.amarsoft.app.ems.employee.template.cs.dto.employeerankrelabellistdto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工职级标签保存请求实体类
 * @author dxiao
 */
@Getter
@Setter
@ToString
public class EmployeeRankRelabelListDtoSaveReq extends EmployeeRankRelabelListDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("职级标签List")
    @NotEmpty
    private List<EmployeeRankRelabelListDto> employeeRankRelabelListDto;
    
    @Description("员工职级流水号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("ER.serialNo")
    private String serialNo;
}
