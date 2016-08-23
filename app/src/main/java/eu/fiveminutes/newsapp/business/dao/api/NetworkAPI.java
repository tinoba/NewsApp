package eu.fiveminutes.newsapp.business.dao.api;


import eu.fiveminutes.newsapp.business.dao.api.models.ApiNews;
import retrofit2.Call;
import retrofit2.http.GET;


public interface NetworkAPI {

    @GET("/svc/search/v2/articlesearch.json?q=new+york+times&sort=newest&api-key=3004f196a796792d1014c857af657567:11:72277667")
    Call<ApiNews> getNews();
}
