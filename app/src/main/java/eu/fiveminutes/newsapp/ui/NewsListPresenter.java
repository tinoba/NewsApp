package eu.fiveminutes.newsapp.ui;

/**
 * Created by tinoba on 18.8.2016..
 */
public interface NewsListPresenter {

    void setView(NewsListView view);

    void loadNews();
}
