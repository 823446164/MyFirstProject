package com.amarsoft.app.amps.plugins;

import org.junit.Test;

import com.amarsoft.amps.dct.excel.CreateEntity;
import com.amarsoft.amps.dct.excel.ExcelEntity;
import com.amarsoft.amps.dct.excel.common.config.AutoCreateConstants;


public class AutoCreateEntity {

    @Test
    public void test() throws Exception {
        AutoCreateConstants.setResourcePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "") + AutoCreateConstants.RESOURCE_DIR);
        AutoCreateConstants.setBasePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").substring(0, AutoCreateCoder.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").lastIndexOf("/") + 1));

        ExcelEntity[] entitys = new ExcelEntity[] {
            //new ExcelEntity("com.amarsoft.app.amps.","安硕消费金融业务服务软件V2.0_进件数据库设计文档.xlsx", 11), 
        };

        CreateEntity.create(entitys);

    }
}
