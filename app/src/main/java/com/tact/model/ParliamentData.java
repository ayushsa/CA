
package com.tact.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParliamentData implements Serializable {

    @SerializedName("ParliamentAreaId")
    @Expose
    private Integer parliamentAreaId;
    @SerializedName("ParliamentAreaName")
    @Expose
    private String parliamentAreaName;

    public Integer getParliamentAreaId() {
        return parliamentAreaId;
    }

    public void setParliamentAreaId(Integer parliamentAreaId) {
        this.parliamentAreaId = parliamentAreaId;
    }

    public String getParliamentAreaName() {
        return parliamentAreaName;
    }

    public void setParliamentAreaName(String parliamentAreaName) {
        this.parliamentAreaName = parliamentAreaName;
    }

}
