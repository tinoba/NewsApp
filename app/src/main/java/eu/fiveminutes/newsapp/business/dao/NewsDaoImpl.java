package eu.fiveminutes.newsapp.business.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.newsapp.model.NewsArticle;

public final class NewsDaoImpl implements NewsDao {
    @Override
    public void deleteNews() {
        final SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        sqLiteDatabase.delete(DatabaseContract.TABLE_ARTICLES,null,null);
        sqLiteDatabase.close();
    }

    private final DatabaseHelper helper;

    public NewsDaoImpl(DatabaseHelper helper) {
        this.helper = helper;
    }

    @Override
    public void insertArticle(final NewsArticle article) {
        final SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.KEY_HEADLINE, article.mainHeadline);
        contentValues.put(DatabaseContract.KEY_SNIPPET, article.snippet);
        contentValues.put(DatabaseContract.KEY_WEB_URL, article.webUrl);

        sqLiteDatabase.insert(DatabaseContract.TABLE_ARTICLES, null, contentValues);
        sqLiteDatabase.close();
    }

    @Override
    public final List<NewsArticle> getAllArticles() {
        final List<NewsArticle> repo = new ArrayList<>();
        final SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        final String selectQuery = "SELECT * FROM " + DatabaseContract.TABLE_ARTICLES;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                final NewsArticle  article = new NewsArticle(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                repo.add(article);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return repo;
    }
}