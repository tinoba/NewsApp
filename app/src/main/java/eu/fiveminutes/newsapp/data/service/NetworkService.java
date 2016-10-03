package eu.fiveminutes.newsapp.data.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkService {

    public static final String BASE_URL = "http://api.nytimes.com";
    private final NewsAPI networkAPI;

    public NetworkService() {
        this(BASE_URL);
    }

    public NetworkService(final String baseUrl) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        networkAPI = retrofit.create(NewsAPI.class);
    }

    public NewsAPI getAPI() {
        return networkAPI;
    }
}
