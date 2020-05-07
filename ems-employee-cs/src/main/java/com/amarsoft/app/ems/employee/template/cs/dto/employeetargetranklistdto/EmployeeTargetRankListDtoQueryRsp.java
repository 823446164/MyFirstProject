package com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 目标职级申请List查询响应实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeTargetRankListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("目标职级申请List")
    private List<EmployeeTargetRankListDto> employeeTargetRankListDtos;
}
