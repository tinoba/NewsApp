package eu.fiveminutes.newsmodel;

/**
 * Created by tinoba on 17.8.2016..
 */
final class Entities {
    public final String lead_paragraph;
    public final String snippet;

    public Entities(String lead_paragraph, String snippet){
        this.lead_paragraph= lead_paragraph;
        this.snippet = snippet;
    }
}
