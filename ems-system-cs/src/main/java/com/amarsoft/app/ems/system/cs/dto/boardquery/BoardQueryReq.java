/**
 * 查询通告
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.boardquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.common.constant.YesNo;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class BoardQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("是否启用权限查询")
    @Length(max=1)
    @Enum(YesNo.class)
    private String authFlag;
    @Description("用户编号")
    @Length(max=40)
    private String userId;
    @Description("通知编号")
    @Length(max=40)
    private String boardId;
    @Description("开始值")
    @Digits(length=10,scale=0)
    private Integer begin;
    @Description("笔数")
    @Digits(length=10,scale=0)
    private Integer pageSize;
}