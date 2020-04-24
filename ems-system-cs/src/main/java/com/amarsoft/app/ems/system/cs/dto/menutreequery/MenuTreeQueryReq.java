/**
 * 查询菜单树图
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.menutreequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;

@Getter
@Setter
@ToString
public class MenuTreeQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("是否显示全部树图")
    @Length(max=1)
    @Enum(YesNo.class)
    private String isShowAllModel;
}