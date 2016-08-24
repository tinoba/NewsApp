package eu.fiveminutes.newsapp.business.dao.api.converter;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.newsapp.business.dao.api.models.ApiDocs;
import eu.fiveminutes.newsapp.business.dao.api.models.ApiMultimedia;
import eu.fiveminutes.newsapp.model.NewsArticle;

public final class ApiConverterImpl implements ApiConverter {

    @Override
    public List<NewsArticle> convertToNewsArticles(final List<ApiDocs> apiDocs) {
        final List<NewsArticle> articles = new ArrayList<>(apiDocs.size());
        for (ApiDocs docs : apiDocs) {
            if (docs.multimedia.isEmpty()){
                articles.add(new NewsArticle(docs.headline.mainHeadline, docs.snippet, docs.webUrl, Uri.EMPTY));
            }else{
                articles.add(new NewsArticle(docs.headline.mainHeadline, docs.snippet, docs.webUrl,docs.multimedia.get(0).url));
            }
        }

        return articles;
    }
}
