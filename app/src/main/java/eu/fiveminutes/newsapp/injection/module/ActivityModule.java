package eu.fiveminutes.newsapp.injection.module;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.injection.scope.ForActivity;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.content.base.activities.BaseActivity;

@Module
public final class ActivityModule {

    private final BaseActivity baseActivity;

    public ActivityModule(final BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @ForActivity
    NewsListAdapter provideNewsListAdapter() {
        return new NewsListAdapter(baseActivity);
    }

    @Provides
    @ForActivity
    public FragmentManager provideFragmentManager() {
        return baseActivity.getSupportFragmentManager();
    }
}
