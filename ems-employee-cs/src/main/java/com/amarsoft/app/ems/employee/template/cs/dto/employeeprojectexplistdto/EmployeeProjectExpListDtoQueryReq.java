/* 文件名：EmployeeProjectExpListDtoQueryReq
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：dxiao
 * 修改时间：2020/05/19
 * 跟踪单号：
 * 修改单号：
 * 修改内容：添加模糊搜索的字段
 */
package com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.acsc.annotation.ActualColumn;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Range;
import com.amarsoft.amps.acsc.query.annotation.QueryBegin;
import com.amarsoft.amps.acsc.query.annotation.QueryOrderBy;
import com.amarsoft.amps.acsc.query.annotation.QueryPageSize;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 员工项目经历List查询请求实体类
 * @author lding
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class EmployeeProjectExpListDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("起始条数")
    @NotEmpty
    @QueryBegin
    private Integer begin;

    @Description("查询笔数")
    @Range(min=1,max=10)
    @NotEmpty
    @QueryPageSize
    private Integer pageSize;

    @Description("排序数组")
    @QueryOrderBy
    private String[] orderBy;

    @Description("员工编号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("EPE.employeeNo")
    private String employeeNo;
    
    @Description("filterList")
    private List<Filter> filters;

}
