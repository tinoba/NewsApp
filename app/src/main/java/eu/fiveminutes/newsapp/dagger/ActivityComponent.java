package eu.fiveminutes.newsapp.dagger;

import android.app.Activity;

import dagger.Component;
import eu.fiveminutes.newsapp.application.ApplicationComponent;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.fragment.NewsDetailFragment;
import eu.fiveminutes.newsapp.ui.fragment.NewsListFragment;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;

@ForActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ActivityModule.class
        }
)
public interface ActivityComponent {

    void injectNewsDetailFragment(NewsDetailFragment fragment);

    void injectNewsListFragment(NewsListFragment fragment);

    final class Initializer {

        private Initializer() {
        }

        static public ActivityComponent init(final ApplicationComponent applicationComponent, final Activity activity) {
            return DaggerActivityComponent.builder()
                                          .applicationComponent(applicationComponent)
                                          .activityModule(new ActivityModule(activity))
                                          .build();
        }
    }

    NewsListPresenter getNewsListPresenter();

    NewsListAdapter getNewsListAdapter();
}

