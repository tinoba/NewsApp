package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiByline {

    @SerializedName("contributor")
    public String contributor;

    @SerializedName("person")
    public ArrayList<ApiPerson> person;

    @SerializedName("original")
    public String original;
}
