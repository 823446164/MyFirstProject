/*
 * 文件名：TeamListDtoDeleteRsp.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：amarsoft
 * 修改时间：2020年5月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.system.cs.dto.teamlistdto;

import java.io.Serializable;


import javax.swing.event.ChangeEvent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author amarsoft
 * @version 2020年5月13日
 * @see TeamListDtoDeleteRsp
 * @since
 */
@Getter
@Setter
@ToString
public class TeamListDtoDeleteRsp  implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    private ChangeEvent ch;

}

