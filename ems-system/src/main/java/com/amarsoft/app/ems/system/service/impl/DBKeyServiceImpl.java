package com.amarsoft.app.ems.system.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.arem.exception.ALSException;
import com.amarsoft.amps.arpe.businessobject.BusinessObjectManager;
import com.amarsoft.amps.arpe.util.DateHelper;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeybatch.DbKeyBatchRsp;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleReq;
import com.amarsoft.app.ems.system.cs.dto.dbkeysingle.DbKeySingleRsp;
import com.amarsoft.app.ems.system.entity.ObjectMaxsn;
import com.amarsoft.app.ems.system.entity.SystemSetup;
import com.amarsoft.app.ems.system.service.DBKeyService;

import lombok.extern.slf4j.Slf4j;

/**
 * 获取流水接口实现类
 * @author cyji
 */
@Slf4j
@Service
public class DBKeyServiceImpl implements DBKeyService {
    private String[] lock = new String[2];
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
     * 获取指定数据表及字段的流水
     * @param table
     * @param column
     * @param dateFormat yyyyMMdd
     * @param noFormat
     * @return
     */
    @Override
    public DbKeySingleRsp getSingleSerialNo(DbKeySingleReq request) {
        DbKeySingleRsp response = new DbKeySingleRsp();
        String table = request.getTable();
        String column = request.getColumn();
        String dateFormat = request.getDateFormat();
        String noFormat = request.getNoFormat();
        
        table = table.toUpperCase();
        column = column.toUpperCase();
        
        int num = 500;
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
                response.setSerialNo(serialNo[0]);
                return response;
            }
        
            String[] sNewSerialNo = serialNo;
            DbKeyBatchReq batchRequest = new DbKeyBatchReq();
            batchRequest.setTable(table);
            batchRequest.setColumn(column);
            batchRequest.setDateFormat(dateFormat);
            batchRequest.setNoFormat(noFormat);
            batchRequest.setNum(num);
            DbKeyBatchRsp result = getBatchSerialNos(batchRequest);
            sNewSerialNo[0] = result.getFromSerialNo();
            sNewSerialNo[1] = result.getToSerialNo();
            
            serialNoPool.put(table+column+dateFormat+noFormat, sNewSerialNo);//将新的流水区间放入缓存中
            response.setSerialNo(sNewSerialNo[0]);
            return response;
        }
    }
    
    /**
     * 批量获取指定数据表及字段的流水，为了避免高并发下出现死锁的情况，在本获取流水号程序中采用指定值更新递归调用的方式，不使用锁表操作方式
     * @param table
     * @param column
     * @param dateFormat
     * @param noFormat
     * @param Num
     * @return
     */
    @Override
    public DbKeyBatchRsp getBatchSerialNos(DbKeyBatchReq request){
        BusinessObjectManager bomanager = BusinessObjectManager.createBusinessObjectManager();
        String table = request.getTable();
        String column = request.getColumn(); 
        String dateFormat = request.getDateFormat(); 
        String noFormat = request.getNoFormat();
        int num = request.getNum();
        
        List<SystemSetup> systemSetups = bomanager.loadBusinessObjects(SystemSetup.class, "BusinessDate is not null");
        if(systemSetups == null||systemSetups.isEmpty())
            throw new ALSException("997003");
        
        LocalDate localDate = DateHelper.getDate(systemSetups.get(0).getBusinessDate());
        
        String date = DateHelper.format(localDate, dateFormat);
        
        table = table.toUpperCase();
        column = column.toUpperCase();
        
        if(num <= 500) num = 500;//默认为500不能太小，影响效率
        DecimalFormat decimalformat = new DecimalFormat(noFormat);
        
        int iDateLen = date.length();
        
        //先从内存中取是否有已获取的未使用的流水
        String[] serialNo = new String[2];    
        String[] sNewSerialNo = serialNo;
        List<ObjectMaxsn> queryobjectmaxsn  = bomanager.loadBusinessObjects(ObjectMaxsn.class,"TableName=:tableName and ColumnName=:columnName and DateFmt=:dataFmt and NoFmt=:noFmt","tableName",table,"columnName",column,"dataFmt",dateFormat,"noFmt",noFormat);
        int iMaxNo = 0;
        if(queryobjectmaxsn!=null&&queryobjectmaxsn.size()>0) {
          String sMaxSerialNo=queryobjectmaxsn.get(queryobjectmaxsn.size()-1).getString("maxSerialno");
          if(sMaxSerialNo != null && (sMaxSerialNo.startsWith(date) || sMaxSerialNo.substring(0, iDateLen).compareTo(date) > 0))
          {
              if(sMaxSerialNo.substring(0, iDateLen).compareTo(date) > 0) {
                  date = sMaxSerialNo.substring(0, iDateLen);
              }
              iMaxNo = Integer.valueOf(sMaxSerialNo.substring(iDateLen)).intValue();
              sNewSerialNo[0] = date + decimalformat.format(iMaxNo + 1);
              sNewSerialNo[1] = date + decimalformat.format(iMaxNo + num);
          } else {
              sNewSerialNo[0] = getInitSerialNo(table, column, dateFormat, noFormat, date);
              iMaxNo = Integer.valueOf(sNewSerialNo[0].substring(iDateLen)).intValue();
              sNewSerialNo[1] = date + decimalformat.format(iMaxNo + num-1);
          }
          int i=bomanager.updateObjectBySql(ObjectMaxsn.class,"MaxSerialNo=:maxSerialNo","TableName=:tableName and ColumnName=:columnName and DateFmt=:dataFmt and NoFmt=:noFmt and MaxSerialNo =:oldMaxSerialNo ","maxSerialNo",sNewSerialNo[1],"tableName",table,"columnName",column,"dataFmt",dateFormat,"noFmt",noFormat,"oldMaxSerialNo",sMaxSerialNo);
          bomanager.commit();
          if(i<=0)//未更新到数据则递归调用该方法重新获取
          {
              return getBatchSerialNos(request);
          }
        } else {
          sNewSerialNo[0] = getInitSerialNo(table, column, dateFormat, noFormat, date);
          iMaxNo = Integer.valueOf(sNewSerialNo[0].substring(iDateLen)).intValue();
          sNewSerialNo[1] = date + decimalformat.format(iMaxNo + num-1);
          try {
              ObjectMaxsn objectMaxsn=new ObjectMaxsn();
              objectMaxsn.setTableName(table);
              objectMaxsn.setColumnName(column);
              objectMaxsn.setMaxSerialno(sNewSerialNo[1]);
              objectMaxsn.setDateFmt(dateFormat);
              objectMaxsn.setNoFmt(noFormat);
              bomanager.updateBusinessObject(objectMaxsn);
              bomanager.updateDB();
              bomanager.commit();
          } catch(Exception e) {
              return getBatchSerialNos(request); 
          }
        }
        bomanager.updateDB();
        bomanager.commit();
        DbKeyBatchRsp response = new DbKeyBatchRsp();
        response.setFromSerialNo(sNewSerialNo[0]);
        response.setToSerialNo(sNewSerialNo[1]);
        return response;
    }
    
    /**
     * 在不存在流水的情况下，通过搜索表实际数据初始化流水初始值
     * @param table
     * @param sColumn
     * @param sDateFmt
     * @param sNoFmt
     * @param date
     * @return
     */
    private String getInitSerialNo(String table, String column, String dateFmt, String noFmt, String date) {
        DecimalFormat dfTemp = new DecimalFormat(noFmt);
        String sNewSerialNo = date + dfTemp.format(1);
        if(log.isDebugEnabled()) {
            log.debug("创建新流水号[" + table + "][" + column + "]=[" + sNewSerialNo + "]");
        }
        return sNewSerialNo;
    }
}
