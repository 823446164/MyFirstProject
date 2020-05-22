/*
 * 文件名：Filter.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xszhou
 * 修改时间：2020年5月19s日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;
import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Filter implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("name")
    private String name;
    
    @Description("option")
    private String option;
    
    @Description("value")
    private String[] value;

}