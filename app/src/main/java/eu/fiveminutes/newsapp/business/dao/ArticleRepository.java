package eu.fiveminutes.newsapp.business.dao;

import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public interface ArticleRepository {

    List<NewsArticle> getAllNews();

    void insertNews(final NewsArticle article);

    void clearNewsTable();
}
