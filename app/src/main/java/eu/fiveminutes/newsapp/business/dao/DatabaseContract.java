package eu.fiveminutes.newsapp.business.dao;

import android.net.Uri;

import eu.fiveminutes.newsapp.model.NewsArticle;

public final class DatabaseContract {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "tasksManager";

    public static final class NewsArticleTable {

        public static final String TABLE_ARTICLES = "articles";

        public static final String KEY_HEADLINE = "headline";
        public static final String KEY_ID = "id";
        public static final String KEY_SNIPPET = "snippet";
        public static final String KEY_WEB_URL = "url";
        public static final String KEY_IMG_URL = "img_url";

        public static final String CREATE_ARTICLES_TABLE = "CREATE TABLE " + TABLE_ARTICLES + "("
                + NewsArticleTable.KEY_ID + " INTEGER PRIMARY KEY, " + NewsArticleTable.KEY_HEADLINE + " TEXT, "
                + NewsArticleTable.KEY_SNIPPET + " TEXT, " + NewsArticleTable.KEY_WEB_URL + " TEXT, " + NewsArticleTable.KEY_IMG_URL + " TEXT)";

        public static final String SELECT_ALL_ARTICLES = "SELECT * FROM " + DatabaseContract.NewsArticleTable.TABLE_ARTICLES;

        public static final String DROP_ARTICLES_TABLE = "DROP TABLE IF EXISTS "+ TABLE_ARTICLES;
    }
}
