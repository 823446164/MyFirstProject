/**
 * 查询系统日期
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.alldatequery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.DatePattern;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.acct.constant.BatchFlag;

@Getter
@Setter
@ToString
public class AllDateQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("系统日期")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String businessDate;
    @Description("批量日期")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String batchDate;
    @Description("投产日期")
    @NotEmpty
    @Length(max=10)
    @DatePattern("yyyy/MM/dd")
    private String productDate;
    @Description("批量标识")
    @NotEmpty
    @Length(max=1)
    @Enum(BatchFlag.class)
    private String batchFlag;
}