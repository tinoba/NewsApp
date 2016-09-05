package eu.fiveminutes.newsapp.dagger;

import android.app.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.business.dao.api.NewsService;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.presenter.NewsDetailPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsDetailPresenterImpl;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenterImpl;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import rx.Scheduler;

import static eu.fiveminutes.newsapp.dagger.ThreadingModule.OBSERVE_SCHEDULER;
import static eu.fiveminutes.newsapp.dagger.ThreadingModule.SUBSCRIBE_SCHEDULER;

@Module
public final class ActivityModule {

    private final Activity activity;

    public ActivityModule(final Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ForActivity
    NewsListPresenter provideNewsListPresenter(ApiConverter apiConverter, NewsService newsService, ArticleRepository articleRepository,
                                               NetworkInformation networkInformation, ResourceUtils resourceUtils, @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler,
                                               @Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler) {

        return new NewsListPresenterImpl(apiConverter, newsService, articleRepository, networkInformation, resourceUtils, observeScheduler, subscribeScheduler);
    }

    @Provides
    @ForActivity
    NewsDetailPresenter provideNewsDetailPresenter() {
        return new NewsDetailPresenterImpl();
    }

    @Provides
    @ForActivity
    NewsListAdapter provideNewsListAdapter() {
        return new NewsListAdapter(activity);
    }
}
