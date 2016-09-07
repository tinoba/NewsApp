package eu.fiveminutes.newsapp.business.dao.api.models;

import com.google.gson.annotations.SerializedName;

public class ApiNews {

    public static final ApiNews EMPTY_API_NEWS = new ApiNews(ApiResponse.EMPT_API_RESPONSE, "", "");

    @SerializedName("response")
    public ApiResponse response;

    @SerializedName("status")
    public String status;

    @SerializedName("copyright")
    public String copyright;

    public ApiNews(final ApiResponse response, final String status, final String copyright) {
        this.response = response;
        this.status = status;
        this.copyright = copyright;
    }
}
