/**
 * 按条件查询机构
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.conditionalorgsquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.Enum;
import com.amarsoft.aecd.system.constant.OrgLevel;
import com.amarsoft.aecd.system.constant.OrgType;
import com.amarsoft.aecd.system.constant.CompanyType;
import com.amarsoft.aecd.common.constant.CountryCode;
import com.amarsoft.amps.acsc.annotation.FunctionCode;
import com.amarsoft.aecd.function.impl.CodeCacheFunction;
import com.amarsoft.aecd.system.constant.SystemStatus;
import com.amarsoft.amps.acsc.annotation.Digits;

@Getter
@Setter
@ToString
public class OrgInfo implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("机构编号")
    @Length(max=40)
    private String orgId;
    @Description("排序编号")
    @Length(max=40)
    private String sortNo;
    @Description("机构名称")
    @Length(max=80)
    private String orgName;
    @Description("级别")
    @Length(max=1)
    @Enum(OrgLevel.class)
    private String orgLevel;
    @Description("机构类型")
    @Length(max=1)
    @Enum({OrgType.class,CompanyType.class})
    private String orgType;
    @Description("父机构代码")
    @Length(max=40)
    private String parentOrgId;
    @Description("所属分行")
    @Length(max=40)
    private String branchOrgId;
    @Description("国家代码（默认中国）")
    @Length(max=3)
    @Enum(CountryCode.class)
    private String country = "CHN";
    @Description("人行金融机构代码")
    @Length(max=40)
    private String bankId;
    @Description("机构辖区")
    @Length(max=6)
    @FunctionCode(value=CodeCacheFunction.class,paramKeys={"codeno"},paramValues={"AreaCode"})
    private String belongArea;
    @Description("核心机构号")
    @Length(max=40)
    private String coreOrgId;
    @Description("机构地址")
    @Length(max=80)
    private String orgAddress;
    @Description("所属法人")
    @Length(max=40)
    private String rootOrg;
    @Description("状态")
    @Length(max=1)
    @Enum(SystemStatus.class)
    private String status;
    @Description("是否为叶子节点")
    private boolean isLeaf;
    @Description("子节点数量")
    @Digits(length=5,scale=0)
    private Integer childNum = 0;

    public boolean getIsLeaf(){
        return this.isLeaf;
    }

    public void setIsLeaf(boolean isLeaf){
        this.isLeaf = isLeaf;
    }
}