package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiMeta {
    @SerializedName("hits")
    public String hits;

    @SerializedName("time")
    public String time;

    @SerializedName("offset")
    public String offset;

}
