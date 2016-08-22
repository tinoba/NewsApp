package eu.fiveminutes.newsapp.application;

import android.app.Application;
import android.net.ConnectivityManager;

import eu.fiveminutes.newsapp.api.NetworkService;
import eu.fiveminutes.newsapp.api.converter.ApiConverter;
import eu.fiveminutes.newsapp.api.converter.ApiConverterImpl;
import eu.fiveminutes.newsapp.business.dao.ArticleRepository;
import eu.fiveminutes.newsapp.business.dao.ArticleRepositoryImpl;
import eu.fiveminutes.newsapp.business.dao.DatabaseHelper;
import eu.fiveminutes.newsapp.business.dao.NewsDao;
import eu.fiveminutes.newsapp.business.dao.NewsDaoImpl;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.NetworkInformationImpl;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenterImpl;


public final class ObjectGraph {

    private ApiConverter apiConverter;
    private NetworkService networkService;
    private ArticleRepository articleRepository;
    private Application application;
    private NetworkInformation networkInformation;

    public ObjectGraph(Application application){
        this.application=application;
    }

    public NetworkInformation getNetworkInformation(){
        if (networkInformation == null){
            networkInformation = new NetworkInformationImpl(getConnectivityManager());
        }
        return networkInformation;
    }

    public ConnectivityManager getConnectivityManager() {
        return (ConnectivityManager) application.getSystemService(application.CONNECTIVITY_SERVICE);
    }

    public ArticleRepository getArticleRepository(){
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
        return new NewsListPresenterImpl(getApiConverter(), getNetworkService(),getArticleRepository(),getNetworkInformation());
    }
}
