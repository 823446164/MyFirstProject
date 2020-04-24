package com.amarsoft.app.ems.system.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;



/**
 * erate_info表的主键类
 */
@Embeddable
public class ErateInfoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private String efficientDate;
    
    private String currency;
    
    public String getEfficientDate() {
        return efficientDate;
    }


    public void setEfficientDate(String efficientDate) {
        this.efficientDate = efficientDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((efficientDate == null) ? 0 : efficientDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErateInfoPK other = (ErateInfoPK) obj;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (efficientDate == null) {
            if (other.efficientDate != null)
                return false;
        } else if (!efficientDate.equals(other.efficientDate))
            return false;
        return true;
    }
    
    
}