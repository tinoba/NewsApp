package eu.fiveminutes.newsapp.application;

import android.app.Application;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class NewsApp extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = new ObjectGraph(this);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
