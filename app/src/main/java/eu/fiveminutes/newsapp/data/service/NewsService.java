package eu.fiveminutes.newsapp.data.service;

import eu.fiveminutes.newsapp.data.api.models.NewsApiModel;
import rx.Single;

public interface NewsService {

    Single<NewsApiModel> getNews();
}
