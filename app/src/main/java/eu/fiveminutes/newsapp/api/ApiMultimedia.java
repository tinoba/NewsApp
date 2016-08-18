package eu.fiveminutes.newsapp.api;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiMultimedia {
    public final String width;
    public final String url;
    public final String height;
    public final String subtype;
    public final ApiLegacy legacy;
    public final String type;

    public ApiMultimedia(String width, String url, String height, String subtype, ApiLegacy legacy, String type) {
        this.width = width;
        this.url = url;
        this.height = height;
        this.subtype = subtype;
        this.legacy = legacy;
        this.type = type;
    }
}
