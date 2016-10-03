package eu.fiveminutes.newsapp.injection;

import android.app.Activity;

import eu.fiveminutes.newsapp.injection.component.ApplicationComponent;
import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.injection.component.ActivityComponent;
import eu.fiveminutes.newsapp.ui.content.base.activities.BaseActivity;

public final class ComponentFactory {

    private ComponentFactory() {}

    public static final ApplicationComponent createApplicationComponent(final NewsApplication newsApplication) {
        return ApplicationComponent.Initializer.init(newsApplication);
    }

    public static final ActivityComponent createActivityComponent(final NewsApplication newsApplication, final BaseActivity activity) {
        return ActivityComponent.Initializer.init(newsApplication.getApplicationComponent(), activity);
    }
}
