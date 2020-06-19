package com.amarsoft.app.amps.plugins;

import org.junit.Test;

import com.amarsoft.app.amps.plugins.CreateEntity;
import com.amarsoft.amps.dct.excel.ExcelEntity;
import com.amarsoft.amps.dct.excel.common.config.AutoCreateConstants;


public class AutoCreateEntity {

    @Test
    public void test() throws Exception {
        AutoCreateConstants.setResourcePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "") + AutoCreateConstants.RESOURCE_DIR);
        AutoCreateConstants.setBasePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").substring(0, AutoCreateCoder.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").lastIndexOf("/") + 1));

        ExcelEntity[] entitys = new ExcelEntity[] {
            new ExcelEntity("com.amarsoft.app.ems.","员工管理模块实体类.xlsx", 1), 
        };

        CreateEntity.create(entitys);

    }
}
