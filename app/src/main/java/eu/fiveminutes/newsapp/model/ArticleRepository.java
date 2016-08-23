package eu.fiveminutes.newsapp.model;

import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public interface ArticleRepository {

    List<NewsArticle> getAllNews();

    void insertNews(final NewsArticle article);

    void clearNewsTable();
}
