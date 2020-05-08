package com.amarsoft.app.ems.employee.template.cs.dto.employeepersonalperformancelistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 员工项目经历个人表现List保存请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
public class EmployeePersonalPerformanceListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工项目经历个人表现List")
    @NotEmpty
    private List<EmployeePersonalPerformanceListDto> employeePersonalPerformanceListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
}
