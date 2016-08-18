package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiMultimedia {
    @SerializedName("width")
    public String width;

    @SerializedName("url")
    public String url;

    @SerializedName("height")
    public String height;

    @SerializedName("subtype")
    public String subtype;

    @SerializedName("legacy")
    public ApiLegacy legacy;

    @SerializedName("type")
    public String type;

}
