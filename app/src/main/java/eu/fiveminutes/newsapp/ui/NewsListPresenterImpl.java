package eu.fiveminutes.newsapp.ui;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.newsapp.api.ApiNews;
import eu.fiveminutes.newsapp.api.NetworkService;
import eu.fiveminutes.newsapp.api.ApiResponse;
import eu.fiveminutes.newsapp.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.api.converter.ApiConverterImpl;
import eu.fiveminutes.newsapp.model.NewsArticle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class NewsListPresenterImpl implements NewsListPresenter {

    private final ApiConverter apiConverter;
    private final NetworkService service;

    public NewsListPresenterImpl(ApiConverter apiConverter, NetworkService service) {
        this.apiConverter = apiConverter;
        this.service = service;
    }

    @Override
    public void loadRetroData() {
        Call<ApiNews> call = service.getAPI().getNews();
        call.enqueue(new Callback<ApiNews>() {
            @Override
            public void onResponse(Call<ApiNews> call, Response<ApiNews> response) {
                //    show in UI
                //      apiConverter.convertToNewsArticles(response.body().response.docs);
            }

            @Override
            public void onFailure(Call<ApiNews> call, Throwable t) {

            }
        });
    }
}
