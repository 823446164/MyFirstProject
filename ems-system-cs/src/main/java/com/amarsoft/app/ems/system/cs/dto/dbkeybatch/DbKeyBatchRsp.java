/**
 * 获取系统流水号（批量）
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.dbkeybatch;

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
public class DbKeyBatchRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("起始流水号")
    @NotEmpty
    @Length(max=40)
    private String fromSerialNo;
    @Description("结束流水号")
    @NotEmpty
    @Length(max=40)
    private String toSerialNo;
}