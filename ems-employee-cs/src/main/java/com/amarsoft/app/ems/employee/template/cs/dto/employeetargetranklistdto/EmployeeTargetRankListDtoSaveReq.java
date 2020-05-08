package com.amarsoft.app.ems.employee.template.cs.dto.employeetargetranklistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 目标职级申请List保存请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeeTargetRankListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("目标职级申请List")
    @NotEmpty
    private List<EmployeeTargetRankListDto> employeeTargetRankListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
}
