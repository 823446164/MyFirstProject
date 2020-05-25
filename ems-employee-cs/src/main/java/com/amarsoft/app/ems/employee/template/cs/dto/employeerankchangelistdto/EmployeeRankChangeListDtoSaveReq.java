package com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangelistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 员工职级调整情况List保存请求实体类
 * @author xucheng
 */
@Getter
@Setter
@ToString
public class EmployeeRankChangeListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工职级调整情况List")
    @NotEmpty
    private List<EmployeeRankChangeListDto> employeeRankChangeListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
}
