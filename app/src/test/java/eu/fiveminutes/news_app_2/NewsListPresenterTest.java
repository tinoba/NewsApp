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
import eu.fiveminutes.newsapp.business.dao.api.models.ApiDocs;
import eu.fiveminutes.newsapp.business.dao.api.models.ApiNews;
import eu.fiveminutes.newsapp.business.dao.api.models.ApiResponse;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenterImpl;
import eu.fiveminutes.newsapp.ui.presenter.NewsListView;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import rx.Completable;
import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

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

    @Before
    public void setUp() throws Exception {
        Scheduler subscribeScheduler = Schedulers.immediate();
        Scheduler observeScheduler = Schedulers.immediate();

        newsListPresenter = new NewsListPresenterImpl(apiConverter, networkService, articleRepository, networkInformation, resourceUtils,
                                                      observeScheduler, subscribeScheduler);
    }

    @Test
    public void DownloadFromApiReturnsTrue() throws Exception {
        ApiNews apiNews = new ApiNews();
        apiNews.response = new ApiResponse();
        apiNews.response.docs = ApiDocs.EMPTY_API_DOCS;
        Mockito.when(networkInformation.isConnected()).thenReturn(true);
        Mockito.when(articleRepository.clearNewsTable()).thenReturn(Completable.complete());
        Mockito.when(networkService.getNews()).thenReturn(Single.just(apiNews));

        newsListPresenter.setView(mockListView);
        newsListPresenter.loadNews();

        Mockito.verify(networkService, Mockito.times(1)).getNews();
        Mockito.verify(mockListView, Mockito.times(0)).showErrorMessage(any());
    }

    @Test
    public void DownloadFromApiReturnsFalse() throws Exception {
        Mockito.when(networkInformation.isConnected()).thenReturn(true);
        Mockito.when(networkService.getNews()).thenReturn(Single.error(new RuntimeException()));

        newsListPresenter.setView(mockListView);
        newsListPresenter.loadNews();

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

        Mockito.verify(articleRepository, Mockito.times(1)).getAllNews();
        Mockito.verifyZeroInteractions(mockListView);
    }

    @Test
    public void downloadFromDatabaseReturnTrue() throws Exception {
        Mockito.when(networkInformation.isConnected()).thenReturn(false);
        Mockito.when(articleRepository.getAllNews()).thenReturn(Single.just(new ArrayList<>()));

        newsListPresenter.setView(mockListView);
        newsListPresenter.loadNews();

        Mockito.verify(mockListView, times(1)).renderView(any());
        Mockito.verify(articleRepository, Mockito.times(1)).getAllNews();
    }
}
