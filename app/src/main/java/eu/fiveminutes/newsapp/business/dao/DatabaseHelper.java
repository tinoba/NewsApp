package eu.fiveminutes.newsapp.business.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TASKS_TABLE = "CREATE TABLE " + DatabaseContract.TABLE_ARTICLES + "("
                + DatabaseContract.KEY_ID + " INTEGER PRIMARY KEY, " + DatabaseContract.KEY_HEADLINE + " TEXT, "
                + DatabaseContract.KEY_SNIPPET + " TEXT, " + DatabaseContract.KEY_WEB_URL + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_ARTICLES);
        onCreate(sqLiteDatabase);
    }
}
