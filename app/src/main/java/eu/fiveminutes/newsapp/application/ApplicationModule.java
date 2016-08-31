package eu.fiveminutes.newsapp.application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final NewsApplication newsApplication;

    public ApplicationModule(final NewsApplication newsApplication) {
        this.newsApplication = newsApplication;
    }

    @Provides
    @Singleton
    NewsApplication provideNewsApplication() {
        return newsApplication;
    }
}
