package eu.fiveminutes.newsapp.business.dao.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ApiResponse {

    public static final ApiResponse EMPT_API_RESPONSE = new ApiResponse(ApiDocs.EMPTY_API_DOCS);

    @SerializedName("docs")
    public List<ApiDocs> docs;

    @SerializedName("meta")
    public ApiMeta meta;

    public ApiResponse(final List<ApiDocs> docs) {
        this.docs = docs;
    }
}
