package eu.fiveminutes.newsapp.domain.usecase;

import java.util.List;

import eu.fiveminutes.newsapp.domain.model.NewsArticle;
import eu.fiveminutes.newsapp.domain.repository.ArticleRepository;
import rx.Completable;
import rx.Single;

public final class GetNewsUseCaseImpl implements GetNewsUseCase{

    public final ArticleRepository articleRepository;

    public GetNewsUseCaseImpl(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Single<List<NewsArticle>> execute() {
        return Single.defer(() -> Single.just(articleRepository.getAllNews()));
    }

    @Override
    public Completable execute(final List<NewsArticle> articles) {
        return Completable.fromAction(() -> articleRepository.insertNews(articles));
    }
}
