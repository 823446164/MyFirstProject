package com.amarsoft.app.ems.demo.template.cs.dto.employeeprojectexplist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工项目经历List查询响应实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
public class EmployeeProjectExpListQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工项目经历List")
    private List<EmployeeProjectExpList> employeeProjectExpLists;
}
