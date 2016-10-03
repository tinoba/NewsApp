package eu.fiveminutes.newsapp.data.api.models;

import com.google.gson.annotations.SerializedName;

public final class ApiHeadline {

    @SerializedName("main")
    public String mainHeadline;

    @SerializedName("content_kicker")
    public String contentKicker;

    @SerializedName("kicker")
    public String kicker;

    @SerializedName("print_headline")
    public String printHeadline;
}
