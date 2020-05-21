package com.amarsoft.app.ems.employee.template.cs.dto.employeerankchangeapplylistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 员工职级调整申请List保存请求实体类
 * @author xucheng
 */
@Getter
@Setter
@ToString
public class EmployeeRankChangeApplyListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("员工职级调整申请List")
    @NotEmpty
    private List<EmployeeRankChangeApplyListDto> employeeRankChangeApplyListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
}
