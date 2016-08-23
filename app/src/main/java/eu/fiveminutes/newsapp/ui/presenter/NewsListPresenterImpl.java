package eu.fiveminutes.newsapp.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.api.ApiNews;
import eu.fiveminutes.newsapp.api.NetworkService;
import eu.fiveminutes.newsapp.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.business.dao.ArticleRepository;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class NewsListPresenterImpl implements NewsListPresenter {

    private final ApiConverter apiConverter;
    private final NetworkService service;
    private final ArticleRepository articleRepository;
    private final NetworkInformation networkInformation;

    private WeakReference<NewsListView> newsListViewWeakReference;

    public NewsListPresenterImpl(ApiConverter apiConverter, NetworkService service, ArticleRepository articleRepository, NetworkInformation networkInformation) {
        this.apiConverter = apiConverter;
        this.service = service;
        this.articleRepository = articleRepository;
        this.networkInformation = networkInformation;
    }

    @Override
    public void setView(final NewsListView view) {
        newsListViewWeakReference = new WeakReference<>(view);
    }

    private void getDataFromApi() {
        Call<ApiNews> call = service.getAPI().getNews();
        call.enqueue(new Callback<ApiNews>() {
            @Override
            public void onResponse(Call<ApiNews> call, Response<ApiNews> response) {
                final NewsListView view = newsListViewWeakReference.get();
                if (view != null) {
                    final List<NewsArticle> articles = apiConverter.convertToNewsArticles(response.body().response.docs);
                    view.renderView(new NewsListViewModel(false, articles, false));
                    addNewTask(articles);
                }
            }

            @Override
            public void onFailure(Call<ApiNews> call, Throwable t) {
                final NewsListView view = newsListViewWeakReference.get();
                if (view != null) {
                    view.showErrorMessage(String.valueOf(R.string.news_api_error_text));
                }
            }
        });
    }

    private void getDataFromDatabase() {
        final NewsListView view = newsListViewWeakReference.get();
        if (view != null) {
            if (articleRepository.getAllNews().isEmpty()) {
                view.renderView(new NewsListViewModel(false, Collections.EMPTY_LIST, true));
            } else {
                view.renderView(new NewsListViewModel(false, articleRepository.getAllNews(), false));
            }
        }
    }

    @Override
    public void loadNews() {
        if (networkInformation.isConnected()) {
            getDataFromApi();
        } else {
            getDataFromDatabase();
        }
    }

    public void addNewTask(final List<NewsArticle> articles) {
        final NewsListView view = newsListViewWeakReference.get();
        if (view != null) {
            AsyncTask<Void, Void, Boolean> asyncTask = new AsyncTask<Void, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(Void... voids) {
                    try {
                        articleRepository.clearNewsTable();
                        for (NewsArticle article : articles) {
                            articleRepository.insertNews(article);
                        }
                        return true;
                    } catch (Exception e) {
                        Log.i("TAG", e.getMessage());
                        return false;
                    }
                }

                @Override
                protected void onPostExecute(Boolean result) {
                    final NewsListView view = newsListViewWeakReference.get();
                    if (view != null) {
                        if (result == true) {
                            Log.i("TAG", "News saved successfully");

                        } else {
                        }
                    }
                }

            };
            asyncTask.execute();
        }

    }
}
