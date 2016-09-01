package eu.fiveminutes.newsapp.business.dao;

import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public interface NewsDao {

    void insertArticle(final List<NewsArticle> article);

    List<NewsArticle> getAllArticles();

    void deleteNews();
}
