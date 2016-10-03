package eu.fiveminutes.newsapp.ui.content.base.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.injection.ComponentFactory;
import eu.fiveminutes.newsapp.injection.component.ActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final NewsApplication newsApplication = (NewsApplication) getApplication();
        initActivityComponent(newsApplication);
        inject(activityComponent);
    }

    public final ActivityComponent getActivityComponent(NewsApplication newsApplication) {
        if (activityComponent == null) {
            initActivityComponent(newsApplication);
        }
        return activityComponent;
    }

    private void initActivityComponent(NewsApplication newsApplication) {
        activityComponent = createActivityComponent(newsApplication);
    }

    protected ActivityComponent createActivityComponent(NewsApplication newsApplication) {
        return ComponentFactory.createActivityComponent(newsApplication, this);
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
