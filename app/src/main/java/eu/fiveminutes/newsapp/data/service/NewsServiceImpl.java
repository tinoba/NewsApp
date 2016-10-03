package eu.fiveminutes.newsapp.data.service;

import eu.fiveminutes.newsapp.data.api.models.NewsApiModel;
import rx.Single;

public final class NewsServiceImpl implements NewsService {

    private final NewsAPI newsAPI;

    public NewsServiceImpl(final NewsAPI newsAPI) {
        this.newsAPI = newsAPI;
    }

    @Override
    public Single<NewsApiModel> getNews() {
        return Single.defer(() -> newsAPI.getNews());
    }
}
