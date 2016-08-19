package eu.fiveminutes.newsapp.ui;

/**
 * Created by tinoba on 18.8.2016..
 */
public interface NewsListPresenter {

    void loadNews();

    void setView(final NewsListView view);
}
