package NewsData;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class MultimediaAPI {
    public final String width;
    public final String url;
    public final String height;
    public final String subtype;
    public final LegacyAPI legacy;
    public final String type;

    public MultimediaAPI(String width, String url, String height, String subtype, LegacyAPI legacy, String type) {
        this.width = width;
        this.url = url;
        this.height = height;
        this.subtype = subtype;
        this.legacy = legacy;
        this.type = type;
    }
}
