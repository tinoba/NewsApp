package eu.fiveminutes.newsapp.model;

import java.util.List;

import rx.Completable;
import rx.Single;

public interface ArticleRepository {

    Single<List<NewsArticle>> getAllNews();

    Completable insertNews(final List<NewsArticle> articles);

    Completable clearNewsTable();
}
