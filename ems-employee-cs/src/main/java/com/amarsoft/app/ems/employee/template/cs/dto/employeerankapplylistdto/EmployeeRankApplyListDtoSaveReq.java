package com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 员工职级申请List保存请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeRankApplyListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工职级申请List")
    @NotEmpty
    private List<EmployeeRankApplyListDto> employeeRankApplyListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
}
