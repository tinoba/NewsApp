package eu.fiveminutes.newsapp.ui.presenter;

import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public interface NewsListView {

    void renderView(List<NewsArticle> newsArticles);
}
