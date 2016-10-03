package eu.fiveminutes.newsapp.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ResponseApiModel {

    public static final ResponseApiModel EMPT_API_RESPONSE = new ResponseApiModel(DocsApiModel.EMPTY_API_DOCS);

    @SerializedName("docs")
    public List<DocsApiModel> docs;

    @SerializedName("meta")
    public ApiMeta meta;

    public ResponseApiModel(final List<DocsApiModel> docs) {
        this.docs = docs;
    }

    public ResponseApiModel() {
    }
}
