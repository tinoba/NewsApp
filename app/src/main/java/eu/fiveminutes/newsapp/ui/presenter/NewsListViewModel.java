package eu.fiveminutes.newsapp.ui.presenter;

import java.util.Collections;
import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public final class NewsListViewModel {

    public static final NewsListViewModel EMPTY = new NewsListViewModel(false, Collections.unmodifiableList(Collections.EMPTY_LIST), false);

    public final boolean showRefreshing;
    public final List<NewsArticle> articles;
    public final boolean showTextBox;

    public NewsListViewModel(boolean showRefreshing, List<NewsArticle> articles, boolean showTextBox) {
        this.showRefreshing = showRefreshing;
        this.articles = articles;
        this.showTextBox = showTextBox;
    }
}
