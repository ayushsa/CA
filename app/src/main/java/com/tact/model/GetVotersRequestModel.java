package com.tact.model;

import com.google.gson.annotations.SerializedName;

public class GetVotersRequestModel
{
    @SerializedName("PsNo")
    private int psNo;

    @SerializedName("AcNo")
    private int AcNo;

    @SerializedName("Prefix")
    private String prefix;

    @SerializedName("UserId")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPsNo()
    {
        return psNo;
    }

    public void setPsNo(int psNo)
    {
        this.psNo = psNo;
    }

    public int getAcNo()
    {
        return AcNo;
    }

    public void setAcNo(int acNo)
    {
        AcNo = acNo;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
}
