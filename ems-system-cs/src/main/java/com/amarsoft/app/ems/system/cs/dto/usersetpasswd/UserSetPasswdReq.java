/**
 * 设置用户密码
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.usersetpasswd;

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
public class UserSetPasswdReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("用户编号")
    @NotEmpty
    @Length(max=40)
    private String userId;
    @Description("忘记密码标识")
    private boolean forgetFlag;
    @Description("旧用户密码")
    @Length(max=100)
    private String oldPassword;
    @Description("新用户密码")
    @NotEmpty
    @Length(max=100)
    private String newPassword;

    public boolean getForgetFlag(){
        return this.forgetFlag;
    }

    public void setForgetFlag(boolean forgetFlag){
        this.forgetFlag = forgetFlag;
    }
}