package com.tact.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetVotersResponseModel extends BaseApiResponse {


    @SerializedName("data")
    private ArrayList<VoterData> voterList = new ArrayList<>();

    public ArrayList<VoterData> getVoterList() {
        return voterList;
    }

    public void setVoterList(ArrayList<VoterData> voterList) {
        this.voterList = voterList;
    }



}
