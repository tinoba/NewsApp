package NewsData;

import java.util.ArrayList;

/**
 * Created by tinoba on 17.8.2016..
 */
public final class ResponseAPI {
    public final ArrayList<DocsAPI> docs;
    public final MetaAPI meta;
    public final String status;
    public final String copyright;

    public ResponseAPI(ArrayList<DocsAPI> docs, MetaAPI meta, String status, String copyright) {
        this.docs = docs;
        this.meta = meta;
        this.status = status;
        this.copyright = copyright;
    }
}
