package eu.fiveminutes.newsapp.injection.component;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.data.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.data.service.NewsService;
import eu.fiveminutes.newsapp.domain.repository.ArticleRepository;
import eu.fiveminutes.newsapp.domain.usecase.ClearNewsDaoUseCase;
import eu.fiveminutes.newsapp.domain.usecase.GetNewsUseCase;
import eu.fiveminutes.newsapp.injection.module.ApiModule;
import eu.fiveminutes.newsapp.injection.module.ApplicationModule;
import eu.fiveminutes.newsapp.injection.module.DataModule;
import eu.fiveminutes.newsapp.injection.module.ThreadingModule;
import eu.fiveminutes.newsapp.injection.module.UseCaseModule;
import eu.fiveminutes.newsapp.injection.module.UtilsModule;
import eu.fiveminutes.newsapp.util.NetworkInformation;
import eu.fiveminutes.newsapp.util.ResourceUtils;
import rx.Scheduler;

import static eu.fiveminutes.newsapp.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static eu.fiveminutes.newsapp.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class,
                UtilsModule.class,
                DataModule.class,
                ThreadingModule.class,
                UseCaseModule.class
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

    ClearNewsDaoUseCase clearDaoUseCase();

    GetNewsUseCase getDaoUseCase();

    @Named(OBSERVE_SCHEDULER)
    Scheduler getObserveScheduler();

    @Named(SUBSCRIBE_SCHEDULER)
    Scheduler getSubscribeScheduler();
}
