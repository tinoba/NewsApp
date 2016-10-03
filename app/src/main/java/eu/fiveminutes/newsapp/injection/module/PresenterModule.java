package eu.fiveminutes.newsapp.injection.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.data.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.data.service.NewsService;
import eu.fiveminutes.newsapp.domain.repository.ArticleRepository;
import eu.fiveminutes.newsapp.domain.usecase.ClearNewsDaoUseCase;
import eu.fiveminutes.newsapp.domain.usecase.GetNewsUseCase;
import eu.fiveminutes.newsapp.injection.scope.ForActivity;
import eu.fiveminutes.newsapp.ui.content.news.presenter.NewsDetailPresenter;
import eu.fiveminutes.newsapp.ui.content.news.presenter.NewsDetailPresenterImpl;
import eu.fiveminutes.newsapp.ui.content.news.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.content.news.presenter.NewsListPresenterImpl;
import eu.fiveminutes.newsapp.ui.content.news.router.NewsRouter;
import eu.fiveminutes.newsapp.util.NetworkInformation;
import eu.fiveminutes.newsapp.util.ResourceUtils;
import rx.Scheduler;

import static eu.fiveminutes.newsapp.injection.module.ThreadingModule.OBSERVE_SCHEDULER;
import static eu.fiveminutes.newsapp.injection.module.ThreadingModule.SUBSCRIBE_SCHEDULER;

@Module
public final class PresenterModule {

    @Provides
    @ForActivity
    NewsListPresenter provideNewsListPresenter(ApiConverter apiConverter, NewsService newsService, ArticleRepository articleRepository,
                                               NetworkInformation networkInformation, ResourceUtils resourceUtils, ClearNewsDaoUseCase clearNewsDaoUseCase,
                                               GetNewsUseCase getNewsUseCase, @Named(OBSERVE_SCHEDULER) Scheduler observeScheduler,
                                               @Named(SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler, NewsRouter newsRouter) {

        return new NewsListPresenterImpl(apiConverter, newsService, networkInformation, resourceUtils, observeScheduler, subscribeScheduler, getNewsUseCase, clearNewsDaoUseCase,
                                         newsRouter);
    }

    @Provides
    @ForActivity
    NewsDetailPresenter provideNewsDetailPresenter() {
        return new NewsDetailPresenterImpl();
    }
}
