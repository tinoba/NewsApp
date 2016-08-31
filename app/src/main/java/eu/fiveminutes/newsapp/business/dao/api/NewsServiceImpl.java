package eu.fiveminutes.newsapp.business.dao.api;

import eu.fiveminutes.newsapp.business.dao.api.models.ApiNews;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public final class NewsServiceImpl implements NewsService{

    private final NewsAPI newsAPI;

    public NewsServiceImpl(final NewsAPI newsAPI) {
        this.newsAPI = newsAPI;
    }

    @Override
    public void getNews(final Callback<ApiNews> callback) {
        final Call<ApiNews> call = newsAPI.getNews();
        call.enqueue(callback);
    }
}
