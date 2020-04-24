package com.amarsoft.app.ems.system.function;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.amarsoft.aecd.function.AbstractFunctionCode;
import com.amarsoft.aecd.function.properties.Item;
import com.amarsoft.app.ems.system.util.OrgHelper;


/**
 * 获取所有机构代码信息
 * @author xjzhao
 */
@Service
public class AllOrgIdFunctionCode extends AbstractFunctionCode{
    
    @Override
    public List<Item> getItems(Map<String, String> paramMap) {
        List<Item> items = new LinkedList<Item>();
        
        String orgLevelTmp = null;
        String orgTypeTmp = null;
        String rootOrgIdTmp = null;
        if(paramMap!=null) orgLevelTmp = paramMap.get("orgLevel");//机构级别，多个逗号分隔
        if(paramMap!=null) orgTypeTmp = paramMap.get("orgType");//机构类型，多个逗号分隔
        if(paramMap!=null) rootOrgIdTmp = paramMap.get("rootOrgId");//所属法人机构，多个逗号分隔
        
        String orgLevel = orgLevelTmp;
        String orgType = orgTypeTmp;
        String rootOrgId = rootOrgIdTmp;
        OrgHelper.getOrgs().forEach(org -> {
            if(!StringUtils.isEmpty(orgLevel)) {
                boolean flag = false;
                for(String level : orgLevel.split(",")) {
                    if(level.equals(org.getOrgLevel())) {
                        flag = true;
                    }
                }
                if(!flag)//匹配不上就不处理
                    return;
            }
            
            if(!StringUtils.isEmpty(orgType)) {
                boolean flag = false;
                for(String type : orgType.split(",")) {
                    if(type.equals(org.getOrgType())) {
                        flag = true;
                    }
                }
                if(!flag)//匹配不上就不处理
                    return;
            }
            
            if(!StringUtils.isEmpty(rootOrgId)) {
                boolean flag = false;
                for(String rootId : rootOrgId.split(",")) {
                    if(rootId.equals(org.getRootOrgId())) {
                        flag = true;
                    }
                }
                if(!flag)//匹配不上就不处理
                    return;
            }
            
            Item item = new Item();
            item.setId(org.getOrgId());
            item.setName(org.getOrgName());
            item.setParent(org.getParentOrgId());
            items.add(item);
        });
        return items;
    }
}
