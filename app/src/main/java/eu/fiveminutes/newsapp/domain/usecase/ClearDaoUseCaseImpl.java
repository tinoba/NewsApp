package eu.fiveminutes.newsapp.domain.usecase;

import eu.fiveminutes.newsapp.domain.repository.ArticleRepository;
import rx.Completable;

public class ClearDaoUseCaseImpl implements ClearNewsDaoUseCase {

    private final ArticleRepository articleRepository;

    public ClearDaoUseCaseImpl(final ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Completable execute() {
        return Completable.fromAction(() -> articleRepository.clearNewsTable());
    }
}
