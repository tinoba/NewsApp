package eu.fiveminutes.newsapp.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.domain.repository.ArticleRepository;
import eu.fiveminutes.newsapp.domain.usecase.ClearDaoUseCaseImpl;
import eu.fiveminutes.newsapp.domain.usecase.ClearNewsDaoUseCase;
import eu.fiveminutes.newsapp.domain.usecase.GetNewsUseCase;
import eu.fiveminutes.newsapp.domain.usecase.GetNewsUseCaseImpl;

@Module
public final class UseCaseModule {

    @Provides
    @Singleton
    ClearNewsDaoUseCase provideClearDaoUseCase(final ArticleRepository articleRepository) {
        return new ClearDaoUseCaseImpl(articleRepository);
    }

    @Provides
    @Singleton
    GetNewsUseCase provideGetNewsUseCase(final ArticleRepository articleRepository) {
        return new GetNewsUseCaseImpl(articleRepository);
    }
}
