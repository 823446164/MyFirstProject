/**
 * 查询用户迁移状态
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.migrationcheckquery;

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
public class MigrationCheckQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("迁移状态")
    @NotEmpty
    @Length(max=1)
    private String migrationStatus;
}