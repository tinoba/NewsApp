package eu.fiveminutes.newsapp.application;

import android.app.Application;
import android.net.ConnectivityManager;

import eu.fiveminutes.newsapp.business.dao.api.NetworkService;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.business.dao.api.converter.ApiConverterImpl;
import eu.fiveminutes.newsapp.model.ArticleRepository;
import eu.fiveminutes.newsapp.model.ArticleRepositoryImpl;
import eu.fiveminutes.newsapp.business.dao.DatabaseHelper;
import eu.fiveminutes.newsapp.business.dao.NewsDao;
import eu.fiveminutes.newsapp.business.dao.NewsDaoImpl;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenterImpl;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.NetworkInformationImpl;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import eu.fiveminutes.newsapp.utils.ResourceUtilsImpl;

public final class ObjectGraph {

    private ApiConverter apiConverter;
    private NetworkService networkService;
    private ArticleRepository articleRepository;
    private Application application;
    private NetworkInformation networkInformation;
    private ResourceUtils resourceUtils;

    public ObjectGraph(Application application) {
        this.application = application;
    }

    public NetworkInformation getNetworkInformation() {
        if (networkInformation == null) {
            networkInformation = new NetworkInformationImpl(getConnectivityManager());
        }
        return networkInformation;
    }

    public ResourceUtils getResourceUtils() {
        if (resourceUtils == null) {
            resourceUtils = new ResourceUtilsImpl(application.getResources());
        }
        return resourceUtils;
    }

    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) application.getSystemService(application.CONNECTIVITY_SERVICE);
    }

    public ArticleRepository getArticleRepository() {
        if (articleRepository == null) {
            articleRepository = new ArticleRepositoryImpl(getNewsDao());
        }

        return articleRepository;
    }

    public NetworkService getNetworkService() {
        if (networkService == null) {
            networkService = new NetworkService();
        }

        return networkService;
    }

    public NewsDao getNewsDao() {
        return new NewsDaoImpl(new DatabaseHelper(application));
    }

    public ApiConverter getApiConverter() {
        if (apiConverter == null) {
            apiConverter = new ApiConverterImpl();
        }

        return apiConverter;
    }

    public NewsListPresenter createNewsListPresenter() {
        return new NewsListPresenterImpl(getApiConverter(), getNetworkService(), getArticleRepository(), getNetworkInformation(), getResourceUtils());
    }
}
