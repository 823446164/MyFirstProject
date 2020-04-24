package com.amarsoft.app.amps.plugins;

import org.junit.Test;

import com.amarsoft.amps.dct.excel.CreateCoder;
import com.amarsoft.amps.dct.excel.ExcelCoder;
import com.amarsoft.amps.dct.excel.common.config.AutoCreateConstants;


/**
 * @author liuxian
 *
 */
public class AutoCreateCoder {
    @Test
    public void test() throws Exception {

        AutoCreateConstants.setResourcePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "") + AutoCreateConstants.RESOURCE_DIR);
        AutoCreateConstants.setBasePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").substring(0, AutoCreateCoder.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").lastIndexOf("/") + 1));

        ExcelCoder[] coders = new ExcelCoder[] { 
            new ExcelCoder("com.amarsoft.app.amps.","asms",false,"ASMS系统管理服务定义.xlsx",1),
            new ExcelCoder("com.amarsoft.app.amps.","abfd",false,"ABFD批量调度流程服务定义.xlsx",1),
            new ExcelCoder("com.amarsoft.app.amps.","apms",false,"APMS产品中心服务定义.xlsx",1),

        };

        CreateCoder.create(coders);
    }
}
