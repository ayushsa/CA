package com.tact.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SignInTwoModel implements Serializable {

    @SerializedName("UserData")
    @Expose
    private UserData userData;
    @SerializedName("ParliamentData")
    @Expose
    private ParliamentData parliamentData;
    @SerializedName("UserACData")
    @Expose
    private List<UserACDatum> userACData = null;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public ParliamentData getParliamentData() {
        return parliamentData;
    }

    public void setParliamentData(ParliamentData parliamentData) {
        this.parliamentData = parliamentData;
    }

    public List<UserACDatum> getUserACData() {
        return userACData;
    }

    public void setUserACData(List<UserACDatum> userACData) {
        this.userACData = userACData;
    }

}