package eu.fiveminutes.newsapp.business.dao.api.models;

import com.google.gson.annotations.SerializedName;

public class ApiNews {

    @SerializedName("response")
    public ApiResponse response;

    @SerializedName("status")
    public String status;

    @SerializedName("copyright")
    public String copyright;
}
