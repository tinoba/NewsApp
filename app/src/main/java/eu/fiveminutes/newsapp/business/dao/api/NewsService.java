package eu.fiveminutes.newsapp.business.dao.api;

import eu.fiveminutes.newsapp.business.dao.api.models.ApiNews;
import rx.Single;

public interface NewsService {

    Single<ApiNews> getNews();
}
