package eu.fiveminutes.newsapp.business.dao;

import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public final class ArticleRepositoryImpl implements ArticleRepository {

    private final NewsDao newsDao;

    public ArticleRepositoryImpl(final NewsDao newsDao){
        this.newsDao = newsDao;
    }

    @Override
    public void clearNewsTable() {
        newsDao.deleteNews();
    }

    @Override
    public void insertNews(final NewsArticle article) {
        newsDao.insertArticle(article);
    }

    @Override
    public List<NewsArticle> getAllNews() {
        return newsDao.getAllArticles();
    }
}
