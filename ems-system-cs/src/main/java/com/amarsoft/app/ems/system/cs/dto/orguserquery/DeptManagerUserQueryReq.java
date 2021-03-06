/*
 * 文件名：DeptManagerUserQueryReq.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：zcluo
 * 修改时间：2020年5月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */
package com.amarsoft.app.ems.system.cs.dto.orguserquery;

import java.io.Serializable;
import java.util.List;
import com.amarsoft.amps.arem.annotation.Description;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeptManagerUserQueryReq implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("filterLIst")
    private List<Filter> filters;
    
}