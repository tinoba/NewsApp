package eu.fiveminutes.newsapp.api;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiMeta {
    public final String hits;
    public final String time;
    public final String offset;

    public ApiMeta(String hits, String time, String offset){
        this.hits = hits;
        this.time = time;
        this.offset = offset;
    }
}
