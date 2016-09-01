package eu.fiveminutes.newsapp.business.dao.api;

import eu.fiveminutes.newsapp.business.dao.api.models.ApiNews;
import rx.Single;

public final class NewsServiceImpl implements NewsService {

    private final NewsAPI newsAPI;

    public NewsServiceImpl(final NewsAPI newsAPI) {
        this.newsAPI = newsAPI;
    }

    @Override
    public Single<ApiNews> getNews() {
        return Single.defer(() -> newsAPI.getNews());
    }
}
