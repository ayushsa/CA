package com.tact.model;

public class PollingStationModel {

    int UserId;
    int AcNo;
    String Prefix;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getAcNo() {
        return AcNo;
    }

    public void setAcNo(int acNo) {
        AcNo = acNo;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
    }
}
