package com.amarsoft.app.ems.employee.template.cs.dto.employeerankapplylistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工职级申请List查询响应实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeRankApplyListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工职级申请List")
    private List<EmployeeRankApplyListDto> employeeRankApplyListDtos;
}
