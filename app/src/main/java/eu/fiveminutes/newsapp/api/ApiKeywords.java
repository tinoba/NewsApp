package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiKeywords {

    @SerializedName("rank")
    public String rank;

    @SerializedName("is_major")
    public String isMajor;

    @SerializedName("name")
    public String name;

    @SerializedName("value")
    public String value;

}
