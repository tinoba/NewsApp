package eu.fiveminutes.newsapp.business.dao.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ApiByline {

    @SerializedName("person")
    public List<ApiPerson> person;

    @SerializedName("original")
    public String original;
}
