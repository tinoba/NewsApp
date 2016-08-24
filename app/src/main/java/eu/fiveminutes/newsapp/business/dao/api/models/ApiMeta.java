package eu.fiveminutes.newsapp.business.dao.api.models;

import com.google.gson.annotations.SerializedName;

public final class ApiMeta {

    @SerializedName("hits")
    public String hits;

    @SerializedName("time")
    public String time;

    @SerializedName("offset")
    public String offset;
}
