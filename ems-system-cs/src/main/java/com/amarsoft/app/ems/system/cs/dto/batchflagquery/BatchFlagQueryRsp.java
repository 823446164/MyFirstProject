/**
 * 查询批量标识
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.batchflagquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.acct.constant.BatchFlag;

@Getter
@Setter
@ToString
public class BatchFlagQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("批量标识")
    @NotEmpty
    @Length(max=1)
    @Enum(BatchFlag.class)
    private String batchFlag;
}