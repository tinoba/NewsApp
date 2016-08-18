package eu.fiveminutes.newsapp.api;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiKeywords {
    public final String rank;
    public final String is_major;
    public final String name;
    public final String value;

    public ApiKeywords(String rank, String is_major, String name, String value) {
        this.rank = rank;
        this.is_major = is_major;
        this.name = name;
        this.value = value;
    }
}
