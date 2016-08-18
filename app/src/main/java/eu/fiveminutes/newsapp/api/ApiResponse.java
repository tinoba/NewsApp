package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by tinoba on 17.8.2016..
 */
public final class ApiResponse {
    @SerializedName("docs")
    public ArrayList<ApiDocs> docs;

    @SerializedName("meta")
    public ApiMeta meta;


}
