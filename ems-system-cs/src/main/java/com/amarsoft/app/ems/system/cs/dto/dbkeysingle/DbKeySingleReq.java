/**
 * 获取系统流水号（单个）
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.dbkeysingle;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;

@Getter
@Setter
@ToString
public class DbKeySingleReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("表名")
    @NotEmpty
    @Length(max=80)
    private String table;
    @Description("列名")
    @NotEmpty
    @Length(max=80)
    private String column;
    @Description("日期模式")
    @Length(max=20)
    private String dateFormat = "yyyyMMdd";
    @Description("流水号模式")
    @Length(max=20)
    private String noFormat = "000000000000";
}