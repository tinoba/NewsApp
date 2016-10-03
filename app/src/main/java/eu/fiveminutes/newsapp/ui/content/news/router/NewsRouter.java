package eu.fiveminutes.newsapp.ui.content.news.router;

import eu.fiveminutes.newsapp.domain.model.NewsArticle;

public interface NewsRouter {

    void goToDetail(NewsArticle article);
}
