package NewsData;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class MetaAPI {
    public final String hits;
    public final String time;
    public final String offset;

    public MetaAPI(String hits, String time, String offset){
        this.hits = hits;
        this.time = time;
        this.offset = offset;
    }
}
