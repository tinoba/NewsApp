package eu.fiveminutes.newsapp.ui.presenter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.business.dao.api.NewsService;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class NewsListPresenterImpl extends BasePresenter implements NewsListPresenter {

    private final ApiConverter apiConverter;
    private final NewsService newsService;
    private final ArticleRepository articleRepository;
    private final NetworkInformation networkInformation;
    private final ResourceUtils resourceUtils;

    private WeakReference<NewsListView> newsListViewWeakReference;

    private List<NewsArticle> articles;

    private boolean loadData = true;

    public NewsListPresenterImpl(final ApiConverter apiConverter, final NewsService newsService,
                                 final ArticleRepository articleRepository, final NetworkInformation networkInformation,
                                 final ResourceUtils resourceUtils) {
        this.apiConverter = apiConverter;
        this.newsService = newsService;
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
        Subscription subscription = newsService.getNews()
                                               .subscribeOn(Schedulers.io())
                                               .observeOn(AndroidSchedulers.mainThread())
                                               .map(apiNews -> apiConverter.convertToNewsArticles(apiNews.response.docs))
                                               .subscribe(newsArticles -> onGetDataFromApiCompleted(newsArticles), throwable -> onGetDataFromApiError());
        addSubscribe(subscription);
    }

    private void onGetDataFromApiError() {
        final NewsListView view = newsListViewWeakReference.get();
        if (view != null) {
            view.showErrorMessage(resourceUtils.getString(R.string.news_api_error_text));
            view.renderView(new NewsListViewModel(false, new ArrayList<>(), false));
        }
    }

    private void onGetDataFromApiCompleted(final List<NewsArticle> newsArticles) {
        final NewsListView view = newsListViewWeakReference.get();
        if (view != null) {
            view.renderView(new NewsListViewModel(false, newsArticles, false));
            NewsListPresenterImpl.this.articles = newsArticles;
            NewsListPresenterImpl.this.addNewsToDatabase(newsArticles);
        }
    }

    private void getDataFromDatabase() {
        Subscription subscription = articleRepository.getAllNews()
                                                     .subscribeOn(Schedulers.io())
                                                     .observeOn(AndroidSchedulers.mainThread())
                                                     .subscribe(newsArticles -> onGetDataFromDatabaseSuccess(newsArticles));
        addSubscribe(subscription);
    }

    private void onGetDataFromDatabaseSuccess(List<NewsArticle> newsArticles) {
        final NewsListView view = newsListViewWeakReference.get();

        if (view != null) {
            if (newsArticles.isEmpty()) {
                view.renderView(new NewsListViewModel(false, new ArrayList<>(), true));
            } else {
                NewsListPresenterImpl.this.articles = newsArticles;
                view.renderView(new NewsListViewModel(false, newsArticles, false));
            }
        }
    }

    private void onDeleteNewsTaskCompleted(List<NewsArticle> articles) {
        Subscription subscription = articleRepository.insertNews(articles)
                                                     .subscribeOn(Schedulers.io())
                                                     .observeOn(AndroidSchedulers.mainThread())
                                                     .subscribe();
        addSubscribe(subscription);
    }

    private void addNewsToDatabase(final List<NewsArticle> articles) {
        Subscription subscription = articleRepository.clearNewsTable()
                                                     .subscribeOn(Schedulers.io())
                                                     .observeOn(AndroidSchedulers.mainThread())
                                                     .subscribe(() -> onDeleteNewsTaskCompleted(articles));
        addSubscribe(subscription);
    }
}
