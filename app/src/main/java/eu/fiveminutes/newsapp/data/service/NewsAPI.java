package eu.fiveminutes.newsapp.data.service;

import eu.fiveminutes.newsapp.data.api.models.NewsApiModel;
import retrofit2.http.GET;
import rx.Single;

public interface NewsAPI {

    @GET("/svc/search/v2/articlesearch.json?q=new+york+times&sort=newest&api-key=3004f196a796792d1014c857af657567:11:72277667")
    Single<NewsApiModel> getNews();
}
