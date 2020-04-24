/**
 * 新增机构内部账户信息
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.orgaccountadd;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.acct.constant.AccountType;
import com.amarsoft.aecd.common.constant.Currency;
import com.amarsoft.aecd.system.constant.SystemStatus;

@Getter
@Setter
@ToString
public class OrgAccountAddReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("流水号")
    @NotEmpty
    @Length(max=40)
    private String serialNo;
    @Description("存款账户类型")
    @NotEmpty
    @Length(max=10)
    @Enum(AccountType.class)
    private String accountType;
    @Description("存款账户账号")
    @NotEmpty
    @Length(max=40)
    private String accountNo;
    @Description("存款账户币种")
    @NotEmpty
    @Length(max=10)
    @Enum(Currency.class)
    private String accountCurrency;
    @Description("存款账户名称")
    @NotEmpty
    @Length(max=80)
    private String accountName;
    @Description("存款账号机构号")
    @NotEmpty
    @Length(max=40)
    private String accountOrgId;
    @Description("状态")
    @Length(max=10)
    @Enum(SystemStatus.class)
    private String status = "1";
}