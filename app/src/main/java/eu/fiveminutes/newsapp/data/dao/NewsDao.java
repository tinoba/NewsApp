package eu.fiveminutes.newsapp.data.dao;

import java.util.List;

import eu.fiveminutes.newsapp.domain.model.NewsArticle;

public interface NewsDao {

    void insertArticle(final List<NewsArticle> article);

    List<NewsArticle> getAllArticles();

    void deleteNews();
}
