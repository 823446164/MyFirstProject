package com.amarsoft.app.ems.system.template.cs.dto.secondleveldeptlistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

/**
 * 二级部门信息List保存请求实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
public class SecondLevelDeptListDtoSaveReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("二级部门信息List")
    @NotEmpty
    private List<SecondLevelDeptListDto> secondLevelDeptListDtos;

    @Description("总笔数")
    private Integer totalCount = 0;
}
