package eu.fiveminutes.newsapp.model;

import java.util.List;

import eu.fiveminutes.newsapp.business.dao.NewsDao;
import rx.Completable;
import rx.Single;

public final class ArticleRepositoryImpl implements ArticleRepository {

    private final NewsDao newsDao;

    public ArticleRepositoryImpl(final NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public Completable clearNewsTable() {
        return Completable.fromAction(()-> newsDao.deleteNews());
    }

    @Override
    public Completable insertNews(final List<NewsArticle> articles) {
        return Completable.fromAction(()->newsDao.insertArticle(articles));
    }

    @Override
    public Single<List<NewsArticle>> getAllNews() {
        return Single.defer(() ->  Single.just(newsDao.getAllArticles()));
    }
}
