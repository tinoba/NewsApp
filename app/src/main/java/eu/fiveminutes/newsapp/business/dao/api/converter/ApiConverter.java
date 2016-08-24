package eu.fiveminutes.newsapp.business.dao.api.converter;

import java.util.List;

import eu.fiveminutes.newsapp.business.dao.api.models.ApiDocs;
import eu.fiveminutes.newsapp.model.NewsArticle;

public interface ApiConverter {

    List<NewsArticle> convertToNewsArticles(List<ApiDocs> apiDocs);
}
