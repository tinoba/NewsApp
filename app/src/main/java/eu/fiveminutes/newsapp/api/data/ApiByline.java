package eu.fiveminutes.newsapp.api.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiByline {

    @SerializedName("person")
    public List<ApiPerson> person;

    @SerializedName("original")
    public String original;
}
