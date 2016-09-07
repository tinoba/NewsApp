package eu.fiveminutes.news_app_2;

import android.net.Uri;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.newsapp.business.dao.DatabaseHelper;
import eu.fiveminutes.newsapp.business.dao.NewsDao;
import eu.fiveminutes.newsapp.business.dao.NewsDaoImpl;
import eu.fiveminutes.newsapp.model.NewsArticle;

public class NewsDaoApplicationTest extends AndroidTestCase {

    private NewsDao newsDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        newsDao = new NewsDaoImpl(new DatabaseHelper(getContext()));
        newsDao.deleteNews();
    }

    @Override
    protected void tearDown() throws Exception {
        newsDao.deleteNews();
        super.tearDown();
    }

    public void testIsNewDaoEmpty() throws Exception {
        final List<NewsArticle> articles = newsDao.getAllArticles();

        Assert.assertNotNull(articles);
        Assert.assertTrue(articles.isEmpty());
    }

    public void testInsertAndQueryMultipleNews() throws Exception {
        final List<NewsArticle> article = new ArrayList<NewsArticle>();

        article.add(new NewsArticle("nesto", "nesto", "nesto", Uri.parse("nesto")));
        article.add(new NewsArticle("nesto2", "nesto2", "nesto2", Uri.parse("nesto2")));

        newsDao.insertArticle(article);
        final List<NewsArticle> articles = newsDao.getAllArticles();

        Assert.assertNotNull(articles);
        Assert.assertEquals(2, articles.size());
    }
}
