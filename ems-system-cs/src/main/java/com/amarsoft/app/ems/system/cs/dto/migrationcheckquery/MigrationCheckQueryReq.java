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
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.MigrationOptional;

@Getter
@Setter
@ToString
public class MigrationCheckQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("迁移机构编号")
    @NotEmpty
    @Length(max=40)
    private String orgId;
    @Description("检查操作类型")
    @Length(max=1)
    @Enum(MigrationOptional.class)
    private String migrationOption;
}