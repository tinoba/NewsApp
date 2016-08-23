package eu.fiveminutes.newsapp.application;

import android.app.Application;

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
