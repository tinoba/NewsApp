package eu.fiveminutes.newsapp.model;

import java.util.List;

public interface ArticleRepository {

    List<NewsArticle> getAllNews();

    void insertNews(final NewsArticle article);

    void clearNewsTable();
}
