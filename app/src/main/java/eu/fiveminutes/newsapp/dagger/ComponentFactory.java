package eu.fiveminutes.newsapp.dagger;

import android.app.Activity;

import eu.fiveminutes.newsapp.application.ApplicationComponent;
import eu.fiveminutes.newsapp.application.NewsApplication;

public class ComponentFactory {

    private ComponentFactory() {}

    public static final ApplicationComponent createApplicationComponent(NewsApplication newsApplication) {
        return ApplicationComponent.Initializer.init(newsApplication);
    }

    public static final ActivityComponent createActivityComponent(NewsApplication newsApplication, Activity activity) {
        return ActivityComponent.Initializer.init(newsApplication.getApplicationComponent(), activity);
    }
}
