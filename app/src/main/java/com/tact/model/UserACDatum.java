
package com.tact.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserACDatum implements Serializable {

    @SerializedName("Prefix")
    @Expose
    private String prefix;
    @SerializedName("AcNo")
    @Expose
    private Integer acNo;
    @SerializedName("AcName")
    @Expose
    private String acName;
    @SerializedName("AcNameOther")
    @Expose
    private String acNameOther;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getAcNo() {
        return acNo;
    }

    public void setAcNo(Integer acNo) {
        this.acNo = acNo;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcNameOther() {
        return acNameOther;
    }

    public void setAcNameOther(String acNameOther) {
        this.acNameOther = acNameOther;
    }

}
