package eu.fiveminutes.newsapp.ui.content.news.presenter;

import eu.fiveminutes.newsapp.domain.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.content.news.view.NewsListView;

public interface NewsListPresenter {

    void loadNews();

    void activate();

    void setView(NewsListView view);

    void unsubscribe();

    void goToDetailFragment(NewsArticle article);
}
