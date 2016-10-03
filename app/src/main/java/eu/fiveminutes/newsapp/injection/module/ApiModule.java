package eu.fiveminutes.newsapp.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.news_app_2.BuildConfig;
import eu.fiveminutes.newsapp.data.service.NewsAPI;
import eu.fiveminutes.newsapp.data.service.NewsService;
import eu.fiveminutes.newsapp.data.service.NewsServiceImpl;
import eu.fiveminutes.newsapp.data.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.data.api.converter.ApiConverterImpl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class ApiModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_API_SERVICE_ENDOPINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    NewsService provideApiNewsSingle(final NewsAPI newsAPI) {
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
