package eu.fiveminutes.newsapp.business.dao.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkService {

    public static final String BASE_URL = "http://api.nytimes.com";
    private final NetworkAPI networkAPI;

    public NetworkService() {
        this(BASE_URL);
    }

    public NetworkService(final String baseUrl) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkAPI = retrofit.create(NetworkAPI.class);
    }

    public NetworkAPI getAPI() {
        return networkAPI;
    }
}
