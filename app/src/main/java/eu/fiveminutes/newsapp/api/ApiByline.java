package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiByline {

    @SerializedName("contributor")
    public String contributor;

    @SerializedName("person")
    public List<ApiPerson> person;

    @SerializedName("original")
    public String original;
}
