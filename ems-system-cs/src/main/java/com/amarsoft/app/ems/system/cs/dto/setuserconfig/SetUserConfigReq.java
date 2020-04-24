/**
 * 获取用户布局
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.setuserconfig;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.Skin;
import com.amarsoft.aecd.common.constant.Language;
import com.amarsoft.aecd.system.constant.Layout;

@Getter
@Setter
@ToString
public class SetUserConfigReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("皮肤类型")
    @Length(max=10)
    @Enum(Skin.class)
    private String skin;
    @Description("语言设置")
    @Length(max=10)
    @Enum(Language.class)
    private String language;
    @Description("用户布局")
    @Length(max=10)
    @Enum(Layout.class)
    private String layout;
}