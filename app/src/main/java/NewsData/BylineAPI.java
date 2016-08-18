package NewsData;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class BylineAPI {
    public final String contributor;
    public final PersonAPI person;
    public final String original;

    public BylineAPI(String contributor, PersonAPI person, String original) {
        this.contributor = contributor;
        this.person = person;
        this.original = original;
    }
}
