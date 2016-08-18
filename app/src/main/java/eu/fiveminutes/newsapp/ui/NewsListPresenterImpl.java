package eu.fiveminutes.newsapp.ui;

import android.util.Log;

import eu.fiveminutes.newsapp.api.NetworkService;
import eu.fiveminutes.newsapp.api.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class NewsListPresenterImpl implements NewsListPresenter {
    private NetworkService service;

    public NewsListPresenterImpl(NetworkService service){
        this.service = service;
    }

    @Override
    public void loadRetroData() {
        Call<ApiResponse> call = service.getAPI().getNews();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("TAG",response.body().copyright);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("TAG","not");
            }
        });
    }
}
