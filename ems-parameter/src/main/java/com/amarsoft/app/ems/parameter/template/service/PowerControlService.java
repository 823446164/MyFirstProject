/*
 * 文件名：PowerToLabelControllerImpl.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：yrong
 * 修改时间：2020年5月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.amarsoft.app.ems.parameter.template.service;
import com.amarsoft.app.ems.parameter.template.controller.impl.PowerControllerImpl;


/**
 * 判断当前用户是否有权限对标签进行维护的service接口
 * @author yrong
 * @version 2020年5月22日
 * @see PowerControllerImpl
 * @since
 */

public interface PowerControlService {
    /**
     * 判断当前用户是否有权限对标签进行维护
     * 返回true则代表有权限，返回false代表无权限
     * @param request
     * @return
     */
    public boolean PowerToLabel();
}
