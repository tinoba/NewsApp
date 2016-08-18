package eu.fiveminutes.newsapp.api;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiByline {
    public final String contributor;
    public final ApiPerson person;
    public final String original;

    public ApiByline(String contributor, ApiPerson person, String original) {
        this.contributor = contributor;
        this.person = person;
        this.original = original;
    }
}
