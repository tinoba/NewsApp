package eu.fiveminutes.newsapp.business.dao.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public final class ApiResponse {

    @SerializedName("docs")
    public List<ApiDocs> docs;

    @SerializedName("meta")
    public ApiMeta meta;


}
