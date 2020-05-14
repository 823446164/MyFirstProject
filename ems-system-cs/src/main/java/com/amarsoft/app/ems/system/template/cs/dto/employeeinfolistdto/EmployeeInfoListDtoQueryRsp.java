package com.amarsoft.app.ems.system.template.cs.dto.employeeinfolistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工详情List查询响应实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
public class EmployeeInfoListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工详情List")
    private List<EmployeeInfoListDto> employeeInfoListDtos;
}
