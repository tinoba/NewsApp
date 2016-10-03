package eu.fiveminutes.newsapp.domain.usecase;

import java.util.List;

import eu.fiveminutes.newsapp.domain.model.NewsArticle;
import rx.Completable;
import rx.Single;

public interface GetNewsUseCase {

    Single<List<NewsArticle>> execute();

    Completable execute(final List<NewsArticle> articles);
}
