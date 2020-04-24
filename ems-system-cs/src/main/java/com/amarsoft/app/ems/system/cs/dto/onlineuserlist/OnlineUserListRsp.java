/**
 * 在线用户查询
 * @Author hzhang23
 * 根据接口定义的excel文档自动生成实体，由AutoCreateCoder.class的test方法批量生成。
 */
package com.amarsoft.app.ems.system.cs.dto.onlineuserlist;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.app.ems.system.cs.dto.userquery.User;
import com.amarsoft.amps.acsc.annotation.Digits;
import javax.validation.Valid;

import java.util.List;

@Getter
@Setter
@ToString
public class OnlineUserListRsp implements Serializable{
    private static final long serialVersionUID = 1L;

    @Description("在线返回总数")
    @Digits(length=40,scale=0)
    private Integer onlineTotalCount;
    @Description("在线客户经理列表")
    @Valid
    private List<User> onlineUsers;
}