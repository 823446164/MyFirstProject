package com.amarsoft.app.ems.employee.template.cs.dto.employeeranklistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工职级List查询响应实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeRankListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工职级List")
    private List<EmployeeRankListDto> employeeRankListDtos;
}
