package eu.fiveminutes.newsapp.model;

import android.net.Uri;

import java.util.List;

public final class NewsArticle {

    public final String mainHeadline;
    public final String snippet;
    public final String webUrl;
    public final Uri imgUri;

    public NewsArticle(final String mainHeadline, final String snippet, final String webUrl, final Uri imgUri) {
        this.webUrl = webUrl;
        this.snippet = snippet;
        this.mainHeadline = mainHeadline;
        this.imgUri = imgUri;
    }
}
