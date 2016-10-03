package eu.fiveminutes.newsapp.data.api.converter;

import java.util.List;

import eu.fiveminutes.newsapp.data.api.models.DocsApiModel;
import eu.fiveminutes.newsapp.domain.model.NewsArticle;

public interface ApiConverter {

    List<NewsArticle> convertToNewsArticles(List<DocsApiModel> apiDocs);
}
