package NewsData;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class HeadlineAPI {
    public final String main;
    public final String content_kicker;
    public final String kicker;
    public final String print_headline;

    public HeadlineAPI(String main, String content_kicker, String kicker, String print_headline) {
        this.main = main;
        this.content_kicker = content_kicker;
        this.kicker = kicker;
        this.print_headline = print_headline;
    }
}
