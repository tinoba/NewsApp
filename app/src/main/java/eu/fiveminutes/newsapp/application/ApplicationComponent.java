package eu.fiveminutes.newsapp.application;

import javax.inject.Singleton;

import dagger.Component;
import eu.fiveminutes.newsapp.business.dao.api.NewsService;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.dagger.ApiModule;
import eu.fiveminutes.newsapp.dagger.ApplicationModule;
import eu.fiveminutes.newsapp.dagger.DatabaseModule;
import eu.fiveminutes.newsapp.dagger.UtilsModule;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.ResourceUtils;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ApiModule.class,
                UtilsModule.class,
                DatabaseModule.class
        }
)

public interface ApplicationComponent {

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

    void inject(NewsApplication newsApplication);
}
