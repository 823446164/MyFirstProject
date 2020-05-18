package com.amarsoft.app.ems.employee.employee.cs.dto.employeeabilitylabels;

import java.io.Serializable;

import com.amarsoft.amps.acsc.annotation.Length;
import com.amarsoft.amps.acsc.annotation.NotEmpty;
import com.amarsoft.amps.arem.annotation.Description;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LabelCatalogInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    @Description("标签编号")
    @NotEmpty
    @Length(max=40)
    private String serialNo;
    @Description("标签名称")
    @NotEmpty
    @Length(max=80)
    private String labelName;
    
}
