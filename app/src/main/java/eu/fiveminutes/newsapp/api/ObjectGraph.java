package eu.fiveminutes.newsapp.api;

import eu.fiveminutes.newsapp.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.api.converter.ApiConverterImpl;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenterImpl;


public final class ObjectGraph {

    private ApiConverter apiConverter;
    private NetworkService networkService;

    public NetworkService getNetworkService() {
        if (networkService == null) {
            networkService = new NetworkService();
        }

        return networkService;
    }

    public ApiConverter getApiConverter() {
        if (apiConverter == null) {
            apiConverter = new ApiConverterImpl();
        }

        return apiConverter;
    }

    public NewsListPresenter createNewsListPresenter() {
        return new NewsListPresenterImpl(getApiConverter(), getNetworkService());
    }
}
