package eu.fiveminutes.newsapp.model;

public final class NewsArticle {

    public final String webUrl;
    public final String snippet;
    public final String mainHeadline;

    public NewsArticle(String webUrl, String snippet, String mainHeadline) {
        this.webUrl = webUrl;
        this.snippet = snippet;
        this.mainHeadline = mainHeadline;
    }
}
