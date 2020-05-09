package com.amarsoft.app.ems.parameter.template.cs.dto.labledescribeinfo;

import java.io.Serializable;
import java.util.List;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.parameter.template.cs.dto.labledescribelist.LableDescribeList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签树图查询响应实体类
 * @author ylgao
 */
@Getter
@Setter
@ToString
public class LableDescribeInfoQueryRsp extends LableDescribeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Description("标签树图")
    private List<LableDescribeInfo> lableDescribeInfos;
}
