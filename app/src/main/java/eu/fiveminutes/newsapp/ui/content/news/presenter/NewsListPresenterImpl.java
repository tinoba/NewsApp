package eu.fiveminutes.newsapp.ui.content.news.presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Named;

import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.data.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.data.service.NewsService;
import eu.fiveminutes.newsapp.domain.model.NewsArticle;
import eu.fiveminutes.newsapp.domain.usecase.ClearNewsDaoUseCase;
import eu.fiveminutes.newsapp.domain.usecase.GetNewsUseCase;
import eu.fiveminutes.newsapp.ui.content.base.presenter.BasePresenter;
import eu.fiveminutes.newsapp.ui.content.news.router.NewsRouter;
import eu.fiveminutes.newsapp.ui.content.news.view.NewsListView;
import eu.fiveminutes.newsapp.ui.content.news.viewmodel.NewsListViewModel;
import eu.fiveminutes.newsapp.util.NetworkInformation;
import eu.fiveminutes.newsapp.util.ResourceUtils;
import rx.Scheduler;

import static eu.fiveminutes.newsapp.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static eu.fiveminutes.newsapp.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

public final class NewsListPresenterImpl extends BasePresenter implements NewsListPresenter {

    private final ApiConverter apiConverter;
    private final NewsService newsService;
    private final NetworkInformation networkInformation;
    private final ResourceUtils resourceUtils;
    private final GetNewsUseCase getNewsUseCase;
    private final ClearNewsDaoUseCase clearNewsDaoUseCase;
    private final Scheduler observeScheduler;
    private final Scheduler subscribeScheduler;
    private final NewsRouter newsRouter;

    private WeakReference<NewsListView> newsListViewWeakReference;

    private List<NewsArticle> articles;

    private boolean loadData = true;

    public NewsListPresenterImpl(final ApiConverter apiConverter, final NewsService newsService, final NetworkInformation networkInformation, final ResourceUtils resourceUtils,
                                 @Named(OBSERVE_SCHEDULER) final Scheduler observeScheduler, @Named(SUBSCRIBE_SCHEDULER) final Scheduler subscribeScheduler,
                                 final GetNewsUseCase getNewsUseCase, final ClearNewsDaoUseCase clearNewsDaoUseCase, NewsRouter newsRouter) {
        this.apiConverter = apiConverter;
        this.newsService = newsService;
        this.networkInformation = networkInformation;
        this.resourceUtils = resourceUtils;
        this.getNewsUseCase = getNewsUseCase;
        this.clearNewsDaoUseCase = clearNewsDaoUseCase;
        this.observeScheduler = observeScheduler;
        this.subscribeScheduler = subscribeScheduler;
        this.newsRouter = newsRouter;
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

    @Override
    public void goToDetailFragment(final NewsArticle article) {
        newsRouter.goToDetail(article);
    }

    private void getDataFromApi() {
        addSubscription(newsService.getNews()
                                   .map(apiNews -> apiConverter.convertToNewsArticles(apiNews.response.docs))
                                   .subscribeOn(subscribeScheduler)
                                   .observeOn(observeScheduler)
                                   .subscribe(this::onGetDataFromApiCompleted, this::onGetDataFromApiError));
    }

    private void onGetDataFromApiError(Throwable throwable) {
        final NewsListView view = newsListViewWeakReference.get();
        if (view != null) {
            view.showErrorMessage(resourceUtils.getString(R.string.news_api_error_text));
            view.renderView(NewsListViewModel.EMPTY);
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
        addSubscription(getNewsUseCase.execute()
                                      .subscribeOn(subscribeScheduler)
                                      .observeOn(observeScheduler)
                                      .subscribe(this::onGetDataFromDatabaseSuccess, this::handleRxJavaError));
    }

    private void handleRxJavaError(final Throwable throwable) {
//        Log.i("TAG", throwable.getMessage());
    }

    private void onGetDataFromDatabaseSuccess(final List<NewsArticle> newsArticles) {
        final NewsListView view = newsListViewWeakReference.get();

        if (view != null) {
            if (newsArticles.isEmpty()) {
                view.renderView(NewsListViewModel.EMPTY);
            } else {
                NewsListPresenterImpl.this.articles = newsArticles;
                view.renderView(new NewsListViewModel(false, newsArticles, false));
            }
        }
    }

    private void onDeleteNewsTaskCompleted(final List<NewsArticle> articles) {
        addSubscription(getNewsUseCase.execute(articles)
                                      .subscribeOn(subscribeScheduler)
                                      .observeOn(observeScheduler)
                                      .subscribe(() -> {
                                      }, this::handleRxJavaError));
    }

    private void addNewsToDatabase(final List<NewsArticle> articles) {
        addSubscription(clearNewsDaoUseCase.execute()
                                           .subscribeOn(subscribeScheduler)
                                           .observeOn(observeScheduler)
                                           .subscribe(() -> onDeleteNewsTaskCompleted(articles), this::handleRxJavaError));
    }
}
