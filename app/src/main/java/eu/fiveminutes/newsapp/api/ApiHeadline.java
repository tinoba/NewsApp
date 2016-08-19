package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiHeadline {

    @SerializedName("contributor")
    public String main;

    @SerializedName("content_kicker")
    public String contentKicker;

    @SerializedName("kicker")
    public String kicker;

    @SerializedName("print_headline")
    public String printHeadline;


}
