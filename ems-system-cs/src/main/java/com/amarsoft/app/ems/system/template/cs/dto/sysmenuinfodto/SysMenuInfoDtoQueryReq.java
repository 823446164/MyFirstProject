package com.amarsoft.app.ems.system.template.cs.dto.sysmenuinfodto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.acsc.query.annotation.QueryRule;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.ActualColumn;

/**
 * 菜单详情Info查询请求实体类
 * @author jcli2
 */
@Getter
@Setter
@ToString
@QueryRule(groupBy = {})
public class SysMenuInfoDtoQueryReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Description("流水号")
    @Length(max=40)
    @NotEmpty
    @ActualColumn("MI.menuId")
    private String menuId;
    @Description("状态")
    @Length(max=1)
    @ActualColumn("MI.status")
    private String status;
}
