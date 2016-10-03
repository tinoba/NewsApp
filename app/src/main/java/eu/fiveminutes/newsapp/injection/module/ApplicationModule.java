package eu.fiveminutes.newsapp.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.application.NewsApplication;

@Module
public final class ApplicationModule {

    private final NewsApplication newsApplication;

    public ApplicationModule(final NewsApplication newsApplication) {
        this.newsApplication = newsApplication;
    }

    @Provides
    @Singleton
    NewsApplication provideApplication() {
        return newsApplication;
    }
}
