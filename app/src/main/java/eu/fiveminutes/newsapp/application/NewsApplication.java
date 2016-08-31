package eu.fiveminutes.newsapp.application;

import android.app.Application;

import eu.fiveminutes.newsapp.dagger.ComponentFactory;

public final class NewsApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = ComponentFactory.createApplicationComponent(this);
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
