package eu.fiveminutes.newsapp.business.dao.api;

import eu.fiveminutes.newsapp.business.dao.api.models.ApiNews;
import retrofit2.Callback;

public interface NewsService {

    void getNews(final Callback<ApiNews> callback);
}
