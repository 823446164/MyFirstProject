/*
 * 文件名：EmployeeListByEmplNoReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：根据条件查询员工列表请求体
 * 修改人：xszhou
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.employee.template.cs.employeelistbyemplno;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.employee.template.cs.dto.employeelistbyuser.Filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeListByEmplNoReq implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Description("员工编号集合")
    private List<String> employeeNoList;
    
    @Description("查询参数数组")
    private List<Filter> filters;
}
