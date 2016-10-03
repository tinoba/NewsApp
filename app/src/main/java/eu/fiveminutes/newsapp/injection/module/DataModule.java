package eu.fiveminutes.newsapp.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.data.dao.DatabaseHelper;
import eu.fiveminutes.newsapp.data.dao.NewsDao;
import eu.fiveminutes.newsapp.data.dao.NewsDaoImpl;
import eu.fiveminutes.newsapp.domain.repository.ArticleRepository;
import eu.fiveminutes.newsapp.domain.repository.ArticleRepositoryImpl;

@Module
public final class DataModule {

    @Provides
    @Singleton
    NewsDao provideNewsDao(final NewsApplication application) {
        return new NewsDaoImpl(new DatabaseHelper(application));
    }

    @Provides
    @Singleton
    ArticleRepository provideArticleRepository(final NewsDao newsDao) {
        return new ArticleRepositoryImpl(newsDao);
    }
}
