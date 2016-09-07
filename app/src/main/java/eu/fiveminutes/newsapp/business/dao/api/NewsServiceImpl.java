package eu.fiveminutes.newsapp.business.dao.api;

import eu.fiveminutes.newsapp.business.dao.api.models.NewsApiModel;
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
