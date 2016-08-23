package eu.fiveminutes.newsapp.api.converter;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.newsapp.api.ApiDocs;
import eu.fiveminutes.newsapp.model.NewsArticle;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiConverterImpl implements ApiConverter {

    @Override
    public List<NewsArticle> convertToNewsArticles(List<ApiDocs> apiDocs) {
        final List<NewsArticle> articles = new ArrayList<>(apiDocs.size());
        for (ApiDocs docs : apiDocs) {
            articles.add(new NewsArticle(docs.headline.mainHeadline,docs.snippet, docs.webUrl));
        }

        return articles;
    }
}
