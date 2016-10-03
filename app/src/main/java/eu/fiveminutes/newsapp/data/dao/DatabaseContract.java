package eu.fiveminutes.newsapp.data.dao;

public final class DatabaseContract {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "tasksManager";
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_INTEGER = "INTEGER";

    public static final class NewsArticleTable {

        public static final String TABLE_ARTICLES = "articles";

        public static final String KEY_HEADLINE = "headline";
        public static final String KEY_ID = "id";
        public static final String KEY_SNIPPET = "snippet";
        public static final String KEY_WEB_URL = "url";
        public static final String KEY_IMG_URL = "img_url";

        public static final String CREATE_ARTICLES_TABLE = "CREATE TABLE " + TABLE_ARTICLES + "("
                + KEY_ID + " "+TYPE_INTEGER+" PRIMARY KEY, " + KEY_HEADLINE + " "+TYPE_TEXT+", "
                + KEY_SNIPPET + " "+TYPE_TEXT+", " + KEY_WEB_URL + " "+TYPE_TEXT+", " + KEY_IMG_URL + " "+TYPE_TEXT+")";

        public static final String SELECT_ALL_ARTICLES = "SELECT * FROM " + TABLE_ARTICLES;

        public static final String DROP_ARTICLES_TABLE = "DROP TABLE IF EXISTS " + TABLE_ARTICLES;
    }
}
