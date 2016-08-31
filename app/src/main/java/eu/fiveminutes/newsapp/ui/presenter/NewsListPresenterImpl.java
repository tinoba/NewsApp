package eu.fiveminutes.newsapp.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.business.dao.api.NewsService;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.business.dao.api.models.ApiNews;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class NewsListPresenterImpl implements NewsListPresenter {

    private final ApiConverter apiConverter;
    private final NewsService service;
    private final ArticleRepository articleRepository;
    private final NetworkInformation networkInformation;
    private final ResourceUtils resourceUtils;

    private WeakReference<NewsListView> newsListViewWeakReference;

    private List<NewsArticle> articles;

    private boolean loadData = true;

    public NewsListPresenterImpl(final ApiConverter apiConverter, final NewsService service,
                                 final ArticleRepository articleRepository, final NetworkInformation networkInformation,
                                 final ResourceUtils resourceUtils) {
        this.apiConverter = apiConverter;
        this.service = service;
        this.articleRepository = articleRepository;
        this.networkInformation = networkInformation;
        this.resourceUtils = resourceUtils;
    }

    @Override
    public void setView(final NewsListView view) {
        newsListViewWeakReference = new WeakReference<>(view);
    }

    @Override
    public void activate() {
        if (loadData) {
            loadNews();
            loadData = false;
        } else {
            final NewsListView view = newsListViewWeakReference.get();
            view.renderView(new NewsListViewModel(false, articles, false));
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

    private void getDataFromApi() {
        service.getNews(new GetNewsCallbackImpl(apiConverter, newsListViewWeakReference, this, resourceUtils));
    }

    private void getDataFromDatabase() {
        new GetArticlesTask(articleRepository, newsListViewWeakReference, this).execute();
    }

    private void addNewsToDatabase(final List<NewsArticle> articles) {
        new SaveArticlesTask(articleRepository, newsListViewWeakReference).execute(articles);
    }

    private static final class GetArticlesTask extends AsyncTask<Void, Void, Boolean> {

        private final ArticleRepository articleRepository;
        private final WeakReference<NewsListView> newsListViewWeakReference;
        private final NewsListPresenterImpl newsListPresenterimpl;

        private List<NewsArticle> articles;

        private GetArticlesTask(final ArticleRepository articleRepository, final WeakReference<NewsListView> newsListViewWeakReference,
                                NewsListPresenterImpl newsListPresenterimpl) {
            this.articleRepository = articleRepository;
            this.newsListViewWeakReference = newsListViewWeakReference;
            this.newsListPresenterimpl = newsListPresenterimpl;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                articles = articleRepository.getAllNews();
                return true;
            } catch (Exception e) {
                Log.i("TAG", e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                final NewsListView view = newsListViewWeakReference.get();
                if (view != null) {
                    if (articles.isEmpty()) {
                        view.renderView(new NewsListViewModel(false, new ArrayList<NewsArticle>(), true));
                    } else {
                        newsListPresenterimpl.articles = articles;
                        view.renderView(new NewsListViewModel(false, articles, false));
                    }
                }
            }
        }
    }

    private static final class GetNewsCallbackImpl implements Callback<ApiNews> {

        private final ApiConverter apiConverter;
        private final WeakReference<NewsListView> newsListViewWeakReference;
        private final ResourceUtils resourceUtils;
        private final NewsListPresenterImpl newsListPresenterimpl;

        private GetNewsCallbackImpl(final ApiConverter apiConverter, final WeakReference<NewsListView> newsListViewWeakReference,
                                    final NewsListPresenterImpl newsListPresenterimpl, final ResourceUtils resourceUtils) {
            this.apiConverter = apiConverter;
            this.newsListViewWeakReference = newsListViewWeakReference;
            this.newsListPresenterimpl = newsListPresenterimpl;
            this.resourceUtils = resourceUtils;
        }

        @Override
        public void onResponse(Call<ApiNews> call, Response<ApiNews> response) {
            final NewsListView view = newsListViewWeakReference.get();
            if (view != null) {
                final List<NewsArticle> articles = apiConverter.convertToNewsArticles(response.body().response.docs);
                view.renderView(new NewsListViewModel(false, articles, false));
                newsListPresenterimpl.articles = articles;
                newsListPresenterimpl.addNewsToDatabase(articles);
            }
        }

        @Override
        public void onFailure(Call<ApiNews> call, Throwable t) {
            final NewsListView view = newsListViewWeakReference.get();
            if (view != null) {
                view.showErrorMessage(resourceUtils.getString(R.string.news_api_error_text));
                view.renderView(new NewsListViewModel(false, new ArrayList<NewsArticle>(), false));
            }
        }
    }

    private static final class SaveArticlesTask extends AsyncTask<List<NewsArticle>, Void, Boolean> {

        private final ArticleRepository articleRepository;
        private final WeakReference<NewsListView> newsListViewWeakReference;

        private SaveArticlesTask(final ArticleRepository articleRepository,
                                 final WeakReference<NewsListView> newsListViewWeakReference) {
            this.articleRepository = articleRepository;
            this.newsListViewWeakReference = newsListViewWeakReference;
        }

        @Override
        protected Boolean doInBackground(List<NewsArticle>... articles) {
            try {
                articleRepository.clearNewsTable();
                for (NewsArticle article : articles[0]) {
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
                if (result) {
                    Log.i("TAG", "News saved successfully");
                }
            }
        }
    }
}
