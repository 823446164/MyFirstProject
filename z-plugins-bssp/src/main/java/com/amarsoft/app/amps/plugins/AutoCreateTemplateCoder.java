package com.amarsoft.app.amps.plugins;

import org.junit.Test;

import com.amarsoft.app.amps.plugins.CreateTemplateCoder;
import com.amarsoft.amps.dct.excel.ExcelTemplate;
import com.amarsoft.amps.dct.excel.common.config.AutoCreateConstants;


/**
 * 该自动生成实体功能，对文档部分格式有要求，目前仅针对模板配置的Excel
 * 
 * @author xxu1
 *
 */
public class AutoCreateTemplateCoder {

    @Test
    public void test() throws Exception {
        AutoCreateConstants.setResourcePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "") + AutoCreateConstants.RESOURCE_DIR);
        AutoCreateConstants.setBasePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").substring(0, AutoCreateCoder.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").lastIndexOf("/") + 1));

        ExcelTemplate[] templates = new ExcelTemplate[] {
            new ExcelTemplate("com.amarsoft.app.ems.","employee",false,"测试模块dto模板.xlsx", 1), 
        };

        CreateTemplateCoder.create(templates);
    }
}
