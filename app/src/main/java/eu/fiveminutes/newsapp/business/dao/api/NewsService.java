package eu.fiveminutes.newsapp.business.dao.api;

import eu.fiveminutes.newsapp.business.dao.api.models.NewsApiModel;
import rx.Single;

public interface NewsService {

    Single<NewsApiModel> getNews();
}
