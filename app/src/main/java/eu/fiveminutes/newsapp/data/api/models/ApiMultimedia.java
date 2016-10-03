package eu.fiveminutes.newsapp.data.api.models;

import com.google.gson.annotations.SerializedName;

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
