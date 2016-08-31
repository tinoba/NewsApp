package eu.fiveminutes.newsapp.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.business.dao.DatabaseHelper;
import eu.fiveminutes.newsapp.business.dao.NewsDao;
import eu.fiveminutes.newsapp.business.dao.NewsDaoImpl;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.model.ArticleRepositoryImpl;

@Module
public final class DatabaseModule {

    @Provides
    @Singleton
    NewsDao provideNewsDao(NewsApplication application) {
        return new NewsDaoImpl(new DatabaseHelper(application));
    }

    @Provides
    @Singleton
    ArticleRepository provideArticleRepository(NewsDao newsDao) {
        return new ArticleRepositoryImpl(newsDao);
    }
}
