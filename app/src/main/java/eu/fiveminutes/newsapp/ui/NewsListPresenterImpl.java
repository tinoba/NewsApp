package eu.fiveminutes.newsapp.ui;

import android.util.Log;
import android.widget.Button;

import java.lang.ref.WeakReference;
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

    private WeakReference<NewsListView> newsListViewWeakReference;

    public NewsListPresenterImpl(ApiConverter apiConverter, NetworkService service) {
        this.apiConverter = apiConverter;
        this.service = service;
    }

    @Override
    public void setView(final NewsListView view) {
        newsListViewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void loadNews() {
        Call<ApiNews> call = service.getAPI().getNews();
        call.enqueue(new Callback<ApiNews>() {
            @Override
            public void onResponse(Call<ApiNews> call, Response<ApiNews> response) {
                final NewsListView view = newsListViewWeakReference.get();
                if (view != null) {
                    view.renderView(apiConverter.convertToNewsArticles(response.body().response.docs));
                }
            }

            @Override
            public void onFailure(Call<ApiNews> call, Throwable t) {

            }
        });
    }
}
