package com.amarsoft.app.ems.demo.template.cs.dto.employeeinfotest;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.demo.template.cs.dto.employeelisttest.EmployeeListTest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工信息Info保存请求实体类
 * @author jfan5
 */
@Getter
@Setter
@ToString
public class EmployeeRankApplySaveReq extends EmployeeInfoTest implements Serializable {
    private static final long serialVersionUID = 1L;
    
}

