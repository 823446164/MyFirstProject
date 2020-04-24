/**
 * 查询用户所属机构
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.userbelongquery;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.userquery.UserBelong;

import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class UserBelongQueryRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("所属信息数组")
    @Valid
    private List<UserBelong> userBelongs;
}