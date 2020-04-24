package com.amarsoft.app.amps.plugins;

import org.junit.Test;

import com.amarsoft.amps.dct.locale.CreateLocaleTW;
import com.amarsoft.amps.dct.locale.LocaleTW;
import com.amarsoft.amps.dct.locale.config.AutoCreateConstants;

/**
 * @author sma4
 * 根据服务名称将服务下的zh-cn.properties文件简体中文转为繁体中文，新的文件名称为zh-tw.properties
 */
public class AutoCreateLocaleTW {
    @Test
    public void test() throws Exception {
        AutoCreateConstants.setBasePath(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").substring(0, AutoCreateCoder.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("/target/classes/", "").lastIndexOf("/")+1));
        
        LocaleTW[] localeTWs = new LocaleTW[]{
            new LocaleTW("abfd,abfd-cs","abfd-cs"),
            new LocaleTW("apms,apms-cs","apms-cs"),
            new LocaleTW("asms,asms-cs","asms-cs"),
            new LocaleTW("apecd","apecd"),
        };
        CreateLocaleTW.create(localeTWs);
    }
}
