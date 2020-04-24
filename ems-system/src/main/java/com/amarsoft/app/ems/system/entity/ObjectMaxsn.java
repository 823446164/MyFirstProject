package com.amarsoft.app.ems.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.amarsoft.amps.arem.annotation.Description;
import com.amarsoft.amps.arpe.businessobject.BusinessObject;


/**
 * 系统流水号表
 */
@Entity
@Description("系统流水号表")
@Table(name="OBJECT_MAXSN")
@IdClass(ObjectMaxsnPK.class)
public class ObjectMaxsn extends BusinessObject {
    private static final long serialVersionUID = 1L;

    /**
         * 表名
     */
    @Description("表名")
    @Id
    @Column(name = "TABLENAME",length=80)
    private String tableName;

    /**
        * 列名
     */
    @Description("列名")
    @Id
    @Column(name = "COLUMNNAME",length=80)
    private String columnName;

    /*
         * 最大数
     */
    @Description("最大数")
    @Column(name = "MAXSERIALNO",length=80,nullable=false)
    private String maxSerialno;

    /**
        * 日期模式
     */
    @Description("日期模式")
    @Id
    @Column(name = "DATEFMT",length=20)
    private String dateFmt;

    /**
        * 流水号模式
     */
    @Description("流水号模式")
    @Id
    @Column(name = "NOFMT",length=20)
    private String noFmt;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getMaxSerialno() {
        return maxSerialno;
    }

    public void setMaxSerialno(String maxSerialno) {
        this.maxSerialno = maxSerialno;
    }

    public String getDateFmt() {
        return dateFmt;
    }

    public void setDateFmt(String dateFmt) {
        this.dateFmt = dateFmt;
    }

    public String getNoFmt() {
        return noFmt;
    }

    public void setNoFmt(String noFmt) {
        this.noFmt = noFmt;
    }

   
}