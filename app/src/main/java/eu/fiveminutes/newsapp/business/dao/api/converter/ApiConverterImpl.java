package eu.fiveminutes.newsapp.business.dao.api.converter;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.newsapp.business.dao.api.models.ApiDocs;
import eu.fiveminutes.newsapp.model.NewsArticle;

import static eu.fiveminutes.news_app_2.BuildConfig.NEWS_API_ENDPOINT;

public final class ApiConverterImpl implements ApiConverter {

    @Override
    public List<NewsArticle> convertToNewsArticles(final List<ApiDocs> apiDocs) {
        final List<NewsArticle> articles = new ArrayList<>(apiDocs.size());
        for (ApiDocs docs : apiDocs) {
            articles.add(mapToNewsArticle(docs));
        }

        return articles;
    }

    private NewsArticle mapToNewsArticle(final ApiDocs docs) {
        if (docs.multimedia.isEmpty()) {
            return new NewsArticle(docs.headline.mainHeadline, docs.snippet, docs.webUrl, Uri.EMPTY);
        } else {
            return new NewsArticle(docs.headline.mainHeadline, docs.snippet, docs.webUrl, getNewsImageUri(docs));
        }
    }

    private Uri getNewsImageUri(final ApiDocs docs) {
        return Uri.parse(NEWS_API_ENDPOINT.concat(docs.multimedia.get(0).url));
    }
}
