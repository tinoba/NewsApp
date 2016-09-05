package eu.fiveminutes.newsapp.ui.presenter;

public interface NewsListPresenter {

    void loadNews();

    void activate();

    void setView(NewsListView view);

    void unsubscribe();
}
