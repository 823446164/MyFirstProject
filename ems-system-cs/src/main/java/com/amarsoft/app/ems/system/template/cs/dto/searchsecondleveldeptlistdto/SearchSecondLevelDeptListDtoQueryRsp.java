package com.amarsoft.app.ems.system.template.cs.dto.searchsecondleveldeptlistdto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import java.util.List;

/**
 * 搜索二级部门信息List查询响应实体类
 * @author zcluo
 */
@Getter
@Setter
@ToString
public class SearchSecondLevelDeptListDtoQueryRsp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("总笔数")
    private Integer totalCount = 0;

    @Description("搜索二级部门信息List")
    private List<SearchSecondLevelDeptListDto> searchSecondLevelDeptListDtos;

}
