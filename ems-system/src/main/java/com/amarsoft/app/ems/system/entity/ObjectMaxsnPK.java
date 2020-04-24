package com.amarsoft.app.ems.system.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * object_maxsn表的主键类
 * 
 */
@Embeddable
public class ObjectMaxsnPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private String tableName;

    private String columnName;

    private String dateFmt;

    private String noFmt;

    public ObjectMaxsnPK() {
    }

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


    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ObjectMaxsnPK)) {
            return false;
        }
        ObjectMaxsnPK castOther = (ObjectMaxsnPK)other;
        return 
            this.tableName.equals(castOther.tableName)
            && this.columnName.equals(castOther.columnName)
            && this.dateFmt.equals(castOther.dateFmt)
            && this.noFmt.equals(castOther.noFmt);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.tableName.hashCode();
        hash = hash * prime + this.columnName.hashCode();
        hash = hash * prime + this.dateFmt.hashCode();
        hash = hash * prime + this.noFmt.hashCode();
        
        return hash;
    }
}