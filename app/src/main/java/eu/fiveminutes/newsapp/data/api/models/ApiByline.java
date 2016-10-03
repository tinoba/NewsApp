package eu.fiveminutes.newsapp.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ApiByline {

    @SerializedName("person")
    public List<ApiPerson> person;

    @SerializedName("original")
    public String original;
}
