package eu.fiveminutes.newsapp.application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import eu.fiveminutes.newsapp.business.dao.api.NewsService;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.dagger.ApiModule;
import eu.fiveminutes.newsapp.dagger.ApplicationModule;
import eu.fiveminutes.newsapp.dagger.DatabaseModule;
import eu.fiveminutes.newsapp.dagger.ThreadingModule;
import eu.fiveminutes.newsapp.dagger.UtilsModule;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import rx.Scheduler;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class,
                UtilsModule.class,
                DatabaseModule.class,
                ThreadingModule.class
        }
)

public interface ApplicationComponent extends ApplicationComponentInjects {

    final class Initializer {

        private Initializer() {
        }

        static public ApplicationComponent init(final NewsApplication newsApplication) {
            return DaggerApplicationComponent.builder()
                                             .applicationModule(new ApplicationModule(newsApplication))
                                             .apiModule(new ApiModule())
                                             .build();
        }
    }

    NewsService getNewsService();

    NetworkInformation getNetworkInformation();

    ResourceUtils getResourceUtils();

    ApiConverter getApiConverter();

    ArticleRepository articleRepository();

    @Named("MainThreadScheduler")
    Scheduler getMainThreadScheduler();

    @Named("IoScheduler")
    Scheduler getIoScheduler();
}
