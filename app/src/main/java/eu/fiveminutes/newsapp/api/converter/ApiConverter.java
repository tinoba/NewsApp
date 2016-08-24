package eu.fiveminutes.newsapp.api.converter;

import java.util.List;

import eu.fiveminutes.newsapp.api.data.ApiDocs;
import eu.fiveminutes.newsapp.model.NewsArticle;

/**
 * Created by tinoba on 18.8.2016..
 */
public interface ApiConverter {

    List<NewsArticle> convertToNewsArticles(List<ApiDocs> apiDocs);
}
