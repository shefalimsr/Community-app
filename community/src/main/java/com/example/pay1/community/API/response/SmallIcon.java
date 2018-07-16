package com.example.pay1.community.API.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmallIcon {

    @SerializedName("res_type")
    @Expose
    private Integer resType;
    @SerializedName("res_url")
    @Expose
    private String resUrl;
    @SerializedName("res_id")
    @Expose
    private Integer resId;

    public Integer getResType() {
        return resType;
    }

    public void setResType(Integer resType) {
        this.resType = resType;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

}
