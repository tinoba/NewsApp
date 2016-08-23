package eu.fiveminutes.newsapp.model;

public final class NewsArticle {

    public final String webUrl;
    public final String snippet;
    public final String mainHeadline;

    public NewsArticle(String mainHeadline, String snippet, String webUrl) {
        this.mainHeadline = mainHeadline;
        this.snippet = snippet;
        this.webUrl = webUrl;
    }
}
