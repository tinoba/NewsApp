package eu.fiveminutes.newsapp.data.api.models;

import com.google.gson.annotations.SerializedName;

public class NewsApiModel {

    public static final NewsApiModel EMPTY_API_NEWS = new NewsApiModel(ResponseApiModel.EMPT_API_RESPONSE);

    @SerializedName("response")
    public ResponseApiModel response;

    @SerializedName("status")
    public String status;

    @SerializedName("copyright")
    public String copyright;

    public NewsApiModel(final ResponseApiModel response) {
        this.response = response;
    }

    public NewsApiModel() {
    }
}
