package com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 员工信息List查询响应实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
public class EmployeeListTestQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("员工信息List")
    private List<EmployeeListTest> employeeListTests;
}
