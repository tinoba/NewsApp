package eu.fiveminutes.newsapp.api;

import java.util.ArrayList;

/**
 * Created by tinoba on 17.8.2016..
 */
public final class ApiResponse {
    public final ArrayList<ApiDocs> docs;
    public final ApiMeta meta;
    public final String status;
    public final String copyright;

    public ApiResponse(ArrayList<ApiDocs> docs, ApiMeta meta, String status, String copyright) {
        this.docs = docs;
        this.meta = meta;
        this.status = status;
        this.copyright = copyright;
    }
}
