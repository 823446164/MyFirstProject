package com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptinfodto;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 二级部门信息Info查询响应实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
public class SecondLevelDeptInfoDtoQueryRsp extends SecondLevelDeptInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    
}
