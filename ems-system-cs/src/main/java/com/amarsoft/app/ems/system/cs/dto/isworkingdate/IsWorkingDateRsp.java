/**
 * 是否为工作日
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.isworkingdate;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.acsc.annotation.NotEmpty;

@Getter
@Setter
@ToString
public class IsWorkingDateRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("是否工作日")
    @NotEmpty
    private boolean isWorkingDate;

    public boolean getIsWorkingDate(){
        return this.isWorkingDate;
    }

    public void setIsWorkingDate(boolean isWorkingDate){
        this.isWorkingDate = isWorkingDate;
    }
}