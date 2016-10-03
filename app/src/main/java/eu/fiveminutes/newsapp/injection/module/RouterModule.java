package eu.fiveminutes.newsapp.injection.module;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.injection.scope.ForActivity;
import eu.fiveminutes.newsapp.ui.content.news.router.NewsRouter;
import eu.fiveminutes.newsapp.ui.content.news.router.NewsRouterImpl;

@Module
public final class RouterModule {

    @Provides
    @ForActivity
    NewsRouter provideNewsRouter(FragmentManager fragmentManager) {
        return new NewsRouterImpl(fragmentManager);
    }
}
