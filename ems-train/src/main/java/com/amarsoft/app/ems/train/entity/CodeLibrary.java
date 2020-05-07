
package com.amarsoft.app.ems.train.entity;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.amarsoft.aecd.common.constant.FormatType;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.annotation.GeneratedKey;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Description("代码仓库表")
@Entity
@Table(name = "CODE_LIBRARY")
@GeneratedKey(autoGenerateKey = true, allocationSize = 1000)
public class CodeLibrary extends BusinessObject {
    private static final long serialVersionUID = 1L;

    @Description("代码号")
    @Id
    @Column(name = "codeNo", nullable = false, length = 40)
    private String codeNo;

    @Description("个项号")
    @Id
    @Column(name = "itemNo", nullable = false, length = 40)
    private String itemNo;

    @Description("排序号")
    @Column(name = "sortNo", length = 40)
    private String sortNo;

    @Description("个项名")
    @Column(name = "itemName", length = 80)
    private String itemName;

    @Description("个项描述")
    @Column(name = "itemDescribe", length = 800)
    private String itemDescribe;

    @Description("个项属性")
    @Column(name = "itemAttribute", length = 800)
    private String itemAttribute;

    @Description("关联代码")
    @Column(name = "relativeCode", length = 4000)
    private String relativeCode;

    @Description("属性字段1")
    @Column(name = "attribute1", length = 250)
    private String attribute1;

    @Description("属性字段2")
    @Column(name = "attribute2", length = 250)
    private String attribute2;

    @Description("属性字段3")
    @Column(name = "attribute3", length = 250)
    private String attribute3;

    @Description("属性字段4")
    @Column(name = "attribute4", length = 250)
    private String attribute4;

    @Description("属性字段5")
    @Column(name = "attribute5", length = 250)
    private String attribute5;

    @Description("属性字段6")
    @Column(name = "attribute6", length = 250)
    private String attribute6;

    @Description("属性字段7")
    @Column(name = "attribute7", length = 250)
    private String attribute7;

    @Description("属性字段8")
    @Column(name = "attribute8", length = 250)
    private String attribute8;

    @Description("备注")
    @Column(name = "remark", length = 250)
    private String remark;

    @Description("是否使用")
    @Column(name = "isInUser", length = 10)
    private String isInUser;

    @Description("登记人")
    @Column(name = "inputUserId", length = 32)
    private String inputUserId;

    @Description("登记机构")
    @Column(name = "inputOrgId", length = 32)
    private String inputOrgId;

    @Description("登记日期")
    @Column(name = "inputDate", length = 20)
    private LocalDateTime inputDate;

    @Description("更新人")
    @Column(name = "updateUserId", length = 32)
    private String updateUserId;

    @Description("更新机构")
    @Column(name = "updateOrgId", length = 32)
    private String updateOrgId;

    @Description("更新时间")
    @Column(name = "updateDate", length = 20)
    private LocalDateTime updateDate;

    public String getInputDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return inputDate.format(dtf);
    }

    public String getUpdateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FormatType.DateTimeFormat.format);
        return updateDate.format(dtf);
    }
}
