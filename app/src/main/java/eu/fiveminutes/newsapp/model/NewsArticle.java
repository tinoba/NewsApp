package eu.fiveminutes.newsapp.model;

import android.net.Uri;

import java.util.List;

public final class NewsArticle {

    public final String webUrl;
    public final String snippet;
    public final String mainHeadline;
    public final Uri imgUri;

    public NewsArticle(final String webUrl, final String snippet, final String mainHeadline, final Uri imgUri) {
        this.webUrl = webUrl;
        this.snippet = snippet;
        this.mainHeadline = mainHeadline;
        this.imgUri = imgUri;
    }
}
