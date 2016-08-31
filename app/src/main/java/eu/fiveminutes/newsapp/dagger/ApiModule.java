package eu.fiveminutes.newsapp.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.news_app_2.BuildConfig;
import eu.fiveminutes.newsapp.business.dao.api.NewsAPI;
import eu.fiveminutes.newsapp.business.dao.api.NewsService;
import eu.fiveminutes.newsapp.business.dao.api.NewsServiceImpl;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverterImpl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class ApiModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_API_SERVICE_ENDOPINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    NewsService provideNewsService(final NewsAPI newsAPI) {
        return new NewsServiceImpl(newsAPI);
    }

    @Provides
    @Singleton
    ApiConverter provideApiConverter(){
        return new ApiConverterImpl();
    }

    @Provides
    @Singleton
    NewsAPI provideNewsAPI(final Retrofit retrofit){
        return retrofit.create(NewsAPI.class);
    }
}
