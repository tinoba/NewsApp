package eu.fiveminutes.newsapp.api;

import eu.fiveminutes.newsapp.ui.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.NewsListPresenterImpl;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ObjectGraph {
    private NetworkService networkService;


    public NetworkService getNetworkService(){
        if (networkService == null) {
            networkService = new NetworkService();
        }

        return networkService;
    }

    public NewsListPresenter createNewsListPresenter() {
        return new NewsListPresenterImpl(getNetworkService());
    }
}
