package eu.fiveminutes.newsapp.business.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.newsapp.business.dao.DatabaseContract.NewsArticleTable;
import eu.fiveminutes.newsapp.model.NewsArticle;

public final class NewsDaoImpl implements NewsDao {

    private final DatabaseHelper helper;

    @Override
    public void deleteNews() {
        final SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        sqLiteDatabase.delete(NewsArticleTable.TABLE_ARTICLES, null, null);
        sqLiteDatabase.close();
    }

    public NewsDaoImpl(DatabaseHelper helper) {
        this.helper = helper;
    }

    @Override
    public void insertArticle(final NewsArticle article) {
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        sqLiteDatabase.insert(NewsArticleTable.TABLE_ARTICLES, null, mapToArticle(article));
        sqLiteDatabase.close();
    }

    @Override
    public final List<NewsArticle> getAllArticles() {
        final SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        final Cursor cursor = sqLiteDatabase.rawQuery(NewsArticleTable.SELECT_ALL_ARTICLES, null);
        if (cursor.moveToFirst()) {
            final List<NewsArticle> repo = new ArrayList<>(cursor.getCount());
            do {
                repo.add(parseToArticle(cursor));
            } while (cursor.moveToNext());
            sqLiteDatabase.close();
            return repo;
        } else {
            sqLiteDatabase.close();
            return new ArrayList<>();
        }
    }

    private ContentValues mapToArticle(final NewsArticle article) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(NewsArticleTable.KEY_HEADLINE, article.mainHeadline);
        contentValues.put(NewsArticleTable.KEY_SNIPPET, article.snippet);
        contentValues.put(NewsArticleTable.KEY_WEB_URL, article.webUrl);
        contentValues.put(NewsArticleTable.KEY_IMG_URL, article);

        return contentValues;
    }

    private NewsArticle parseToArticle(final Cursor cursor) {
        return new NewsArticle(cursor.getString(cursor.getColumnIndex(NewsArticleTable.KEY_HEADLINE)),
                               cursor.getString(cursor.getColumnIndex(NewsArticleTable.KEY_SNIPPET)),
                               cursor.getString(cursor.getColumnIndex(NewsArticleTable.KEY_WEB_URL)));
    }
}