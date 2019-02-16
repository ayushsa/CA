package com.tact.model;

import com.google.gson.annotations.SerializedName;

public class SyncedVoterResponseModel extends BaseApiResponse {


    @SerializedName("data")
    VoterData voterData;

    public VoterData getVoterList() {
        return voterData;
    }

    public void setVoterList(VoterData voterList) {
        this.voterData = voterList;
    }


}
