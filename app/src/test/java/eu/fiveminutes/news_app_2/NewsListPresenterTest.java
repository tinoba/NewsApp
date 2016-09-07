package eu.fiveminutes.news_app_2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import eu.fiveminutes.newsapp.business.dao.api.NewsService;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.business.dao.api.models.NewsApiModel;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenterImpl;
import eu.fiveminutes.newsapp.ui.presenter.NewsListView;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import rx.Completable;
import rx.Single;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class NewsListPresenterTest {

    @Mock
    public NetworkInformation networkInformation;

    @Mock
    private NewsListView newsListView;

    @Mock
    private NewsListPresenter newsListPresenter;
    @Mock
    private ApiConverter apiConverter;

    @Mock
    private NewsService networkService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ResourceUtils resourceUtils;

    @Mock
    private NewsListView mockListView;

    private final TestScheduler testScheduler = Schedulers.test();

    @Before
    public void setUp() throws Exception {
        newsListPresenter = new NewsListPresenterImpl(apiConverter, networkService, articleRepository, networkInformation, resourceUtils,
                                                      testScheduler, testScheduler);
    }

    @Test
    public void downloadFromApiReturnsTrue() throws Exception {
        Mockito.when(networkInformation.isConnected()).thenReturn(true);
        Mockito.when(articleRepository.clearNewsTable()).thenReturn(Completable.complete());
        Mockito.when(networkService.getNews()).thenReturn(Single.just(NewsApiModel.EMPTY_API_NEWS));

        newsListPresenter.setView(mockListView);
        newsListPresenter.loadNews();

        testScheduler.triggerActions();

        Mockito.verify(networkService, Mockito.times(1)).getNews();
        Mockito.verify(mockListView, Mockito.never()).showErrorMessage(any());
        Mockito.verify(apiConverter, Mockito.times(1)).convertToNewsArticles(any());
        Mockito.verify(mockListView, Mockito.times(1)).renderView(any());
    }

    @Test
    public void downloadFromApiReturnsFalse() throws Exception {
        Mockito.when(networkInformation.isConnected()).thenReturn(true);
        Mockito.when(networkService.getNews()).thenReturn(Single.error(new RuntimeException()));

        newsListPresenter.setView(mockListView);
        newsListPresenter.loadNews();

        testScheduler.triggerActions();

        verifyZeroInteractions(articleRepository);
        Mockito.verify(networkService, Mockito.times(1)).getNews();
        Mockito.verify(mockListView, Mockito.times(1)).showErrorMessage(any());
    }

    @Test
    public void downloadFromDatabaseReturnFalse() throws Exception {
        Mockito.when(networkInformation.isConnected()).thenReturn(false);
        Mockito.when(articleRepository.getAllNews()).thenReturn(Single.error(new RuntimeException()));

        newsListPresenter.setView(mockListView);
        newsListPresenter.loadNews();

        testScheduler.triggerActions();

        Mockito.verify(articleRepository, Mockito.times(1)).getAllNews();
        Mockito.verifyZeroInteractions(mockListView);
    }

    @Test
    public void downloadFromDatabaseReturnTrue() throws Exception {
        Mockito.when(networkInformation.isConnected()).thenReturn(false);
        Mockito.when(articleRepository.getAllNews()).thenReturn(Single.just(new ArrayList<>()));

        newsListPresenter.setView(mockListView);
        newsListPresenter.loadNews();

        testScheduler.triggerActions();

        Mockito.verify(mockListView, times(1)).renderView(any());
        Mockito.verify(articleRepository, Mockito.times(1)).getAllNews();
    }
}
