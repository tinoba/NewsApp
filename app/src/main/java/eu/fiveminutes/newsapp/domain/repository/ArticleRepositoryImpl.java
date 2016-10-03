package eu.fiveminutes.newsapp.domain.repository;

import java.util.List;

import eu.fiveminutes.newsapp.data.dao.NewsDao;
import eu.fiveminutes.newsapp.domain.model.NewsArticle;

public final class ArticleRepositoryImpl implements ArticleRepository {

    private final NewsDao newsDao;

    public ArticleRepositoryImpl(final NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public void clearNewsTable() {
        newsDao.deleteNews();
    }

    @Override
    public void insertNews(final List<NewsArticle> articles) {
        newsDao.insertArticle(articles);
    }

    @Override
    public List<NewsArticle> getAllNews() {
        return newsDao.getAllArticles();
    }
}
