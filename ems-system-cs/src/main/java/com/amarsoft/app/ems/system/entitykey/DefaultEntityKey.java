package com.amarsoft.app.ems.system.entitykey;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.acsc.rpc.RequestMessage;
import com.amarsoft.amps.arpe.entitykey.EntityKey;
import com.amarsoft.app.ems.system.cs.client.DBKeyClient;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchRsp;

/**
 * BusinessObject实体对象主键或流水的生成器
 *
 * @author xjzhao
 */

@Component
@ConditionalOnClass(EntityKey.class)
public class DefaultEntityKey implements EntityKey{
    private String[] lock = new String[2];
    @Autowired
    private DBKeyClient dbKeyClient;
    /**
     * 定义数据库表流水缓存池：
     *  其用法与数据库最大流水表类似
     *      key=表名+字段名+日期格式+数值流水格式
     *      value=[流水起始值,流水终止值]
     * 其设计意义：
     * 1、每次按批次获取流水，获取流水批次后放入缓存对象中，程序在使用时直接从缓存读取，减少数据库与应用之间的交互加快效率
     * 2、通过java中同步机制在获取缓存流水时，多线程之间不会出现获取到同一流水的情况
     * 3、通过数据库层面数据操作同步机制，在多进程情况下也不会存在获取到同一流水的情况
     */
    private static HashMap<String,String[]> serialNoPool = new HashMap<String,String[]>();
    
    /**
     * 批量获取指定数据表及字段的流水
     * @param table
     * @param column
     * @param dateFormat yyyyMMdd
     * @param noFormat
     * @param num
     * @return
     */
    public String getSerialNo(String table, String column, String dateFormat, String noFormat,int num) {
        
        table = table.toUpperCase();
        column = column.toUpperCase();
        
        if(num <= 0) num = 500;
        DecimalFormat decimalformat = new DecimalFormat(noFormat);
        
        int iDateLen = dateFormat.length();
        
        //先从内存中取是否有已获取的未使用的流水
        String[] serialNo = (String[])serialNoPool.get(table+column+dateFormat+noFormat);
        //进行单表单字段锁定
        Object lockObject = serialNo;//默认基于数组进行锁,当数组为空时,使用声明的源数组进行lock，因为服务段采用递归调用的方式获取流水，所以这里必须进行同步锁
        if(serialNo == null)
        {
            serialNo = new String[2];
            lockObject = lock;
        }
        
        synchronized(lockObject) {//增对缓存对象进行同步锁
            //缓存中存在、缓存中的流水未使用完、缓存中的流水必须匹配传入日期
            if(!StringUtils.isEmpty(serialNo[0]) 
                    && !StringUtils.isEmpty(serialNo[1])
                    && !serialNo[0].equals(serialNo[1]) ) {
                
                String date = serialNo[0].substring(0, iDateLen);
            
                long iMaxNo = Long.valueOf(serialNo[0].substring(iDateLen)).longValue();
                serialNo[0] = date + decimalformat.format(iMaxNo + 1);
                return serialNo[0];
            }
        
            String[] sNewSerialNo = serialNo;
            DbKeyBatchReq dkr = new DbKeyBatchReq();
            dkr.setTable(table);
            dkr.setColumn(column);
            dkr.setDateFormat(dateFormat);
            dkr.setNoFormat(noFormat);
            dkr.setNum(num);
            //调用http服务获取流水
            DbKeyBatchRsp result = dbKeyClient.dbKeyBatch(new RequestMessage<DbKeyBatchReq>(dkr)).getBody().getMessage();
            sNewSerialNo[0] = result.getFromSerialNo();
            sNewSerialNo[1] = result.getToSerialNo();
            
            serialNoPool.put(table+column+dateFormat+noFormat, sNewSerialNo);//将新的流水区间放入缓存中
            return sNewSerialNo[0];
        }
    }
}

