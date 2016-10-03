package eu.fiveminutes.newsapp.domain.repository;

import java.util.List;

import eu.fiveminutes.newsapp.domain.model.NewsArticle;
import rx.Completable;

public interface ArticleRepository {

    List<NewsArticle> getAllNews();

    void insertNews(final List<NewsArticle> articles);

    void clearNewsTable();
}
