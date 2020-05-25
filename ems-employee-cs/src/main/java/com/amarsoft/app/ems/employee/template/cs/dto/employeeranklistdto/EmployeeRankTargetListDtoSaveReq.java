package com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto;


import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;


/**
 * 员工职级List保存请求实体类
 * 
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeRankTargetListDtoSaveReq extends EmployeeRankListDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Description("员工编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("ER.employeeNo")
    private String employeeNo;
}
