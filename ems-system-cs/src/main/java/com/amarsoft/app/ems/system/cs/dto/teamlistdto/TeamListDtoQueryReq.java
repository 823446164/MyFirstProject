/*
 * 文件名：TeamListDtoQueryReq
 * 版权：Copyright by www.amarsoft.com
 * 描述：团队信息查询请求实体类
 * 修改人：xszhou
 * 修改时间：2020/5/21
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 团队信息查询请求实体类
 * @author hpli
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class TeamListDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("开始值")
    private Integer begin;
    
    @Description("笔数")
    private Integer pageSize;
    
    @Description("所属部门")
    private String  orgId;
    
    @Description("查询条件")
    private List<Filter> filters; 

}
