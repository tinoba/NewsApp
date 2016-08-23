package eu.fiveminutes.newsapp.ui.presenter;

import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public class NewsListViewModel {

    public final boolean showRefreshing;
    public final List<NewsArticle> articles;

    public NewsListViewModel(boolean showRefreshing, List<NewsArticle> articles) {
        this.showRefreshing = showRefreshing;
        this.articles = articles;
    }
}
