package eu.fiveminutes.newsapp.dagger;

import android.app.Activity;

import eu.fiveminutes.newsapp.application.ApplicationComponent;
import eu.fiveminutes.newsapp.application.NewsApplication;

public final class ComponentFactory {

    private ComponentFactory() {}

    public static final ApplicationComponent createApplicationComponent(final NewsApplication newsApplication) {
        return ApplicationComponent.Initializer.init(newsApplication);
    }

    public static final ActivityComponent createActivityComponent(final NewsApplication newsApplication, final Activity activity) {
        return ActivityComponent.Initializer.init(newsApplication.getApplicationComponent(), activity);
    }
}
