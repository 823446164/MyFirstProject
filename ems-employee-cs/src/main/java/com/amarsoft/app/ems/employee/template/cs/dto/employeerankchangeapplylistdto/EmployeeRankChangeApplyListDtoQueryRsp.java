package com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工职级调整申请List查询响应实体类
 * @author xucheng
 */
@Getter
@Setter
@ToString
public class EmployeeRankChangeApplyListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工职级调整申请List")
    private List<EmployeeRankChangeApplyListDto> employeeRankChangeApplyListDtos;
}
