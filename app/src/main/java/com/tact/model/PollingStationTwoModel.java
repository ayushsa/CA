package com.tact.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PollingStationTwoModel {

    @SerializedName("PsNo")
    @Expose
    private Integer psNo;
    @SerializedName("PsName")
    @Expose
    private String psName;
    @SerializedName("PsNameOther")
    @Expose
    private String psNameOther;

    public Integer getPsNo() {
        return psNo;
    }

    public void setPsNo(Integer psNo) {
        this.psNo = psNo;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getPsNameOther() {
        return psNameOther;
    }

    public void setPsNameOther(String psNameOther) {
        this.psNameOther = psNameOther;
    }

}