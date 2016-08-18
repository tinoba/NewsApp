package NewsData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class Network {
    private static String baseUrl ="http://api.nytimes.com";
    private NetworkAPI networkAPI;
    public Network(){
        this(baseUrl);
    }
    public Network(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkAPI = retrofit.create(NetworkAPI.class);
    }
    public NetworkAPI getAPI(){
        return  networkAPI;
    }
}
