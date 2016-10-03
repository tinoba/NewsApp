package eu.fiveminutes.newsapp.data.api.models;

import com.google.gson.annotations.SerializedName;

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
