package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinoba on 18.8.2016..
 */
public class ApiNews {

    @SerializedName("response")
    public  ApiResponse response;

    @SerializedName("status")
    public String status;

    @SerializedName("copyright")
    public String copyright;
}
