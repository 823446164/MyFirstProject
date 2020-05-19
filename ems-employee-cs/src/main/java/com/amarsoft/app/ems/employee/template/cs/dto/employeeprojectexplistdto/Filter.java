/*
 * 文件名：Filter.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：搜索框实体类
 * 修改人：dxiao
 * 修改时间：2020年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：新生成
 */

package com.amarsoft.app.ems.employee.template.cs.dto.employeeprojectexplistdto;

import java.io.Serializable;

import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author dxiao
 * @version 2020年5月19日
 * @see Filter
 * @since
 */
@Getter
@Setter
@ToString
public class Filter implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("name")
    private String name;
    @Description("option")
    private String option;
    @Description("value")
    private String[] value;

}
