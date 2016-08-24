package eu.fiveminutes.newsapp.api;



import eu.fiveminutes.newsapp.api.data.ApiNews;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tinoba on 18.8.2016..
 */
public interface NetworkAPI {

    @GET("/svc/search/v2/articlesearch.json?q=new+york+times&sort=newest&api-key=3004f196a796792d1014c857af657567:11:72277667")
    Call<ApiNews> getNews();
}
