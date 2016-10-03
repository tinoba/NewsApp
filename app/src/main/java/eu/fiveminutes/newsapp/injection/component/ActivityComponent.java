package eu.fiveminutes.newsapp.injection.component;

import dagger.Component;
import eu.fiveminutes.newsapp.injection.module.ActivityModule;
import eu.fiveminutes.newsapp.injection.module.PresenterModule;
import eu.fiveminutes.newsapp.injection.module.RouterModule;
import eu.fiveminutes.newsapp.injection.scope.ForActivity;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.content.base.activities.BaseActivity;
import eu.fiveminutes.newsapp.ui.content.news.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.content.news.router.NewsRouter;

@ForActivity
@Component(
        dependencies = {
                ApplicationComponent.class
        },
        modules = {
                ActivityModule.class,
                PresenterModule.class,
                RouterModule.class
        }
)
public interface ActivityComponent extends ActivityComponentActivityInjects, ActivityComponentFragmentsInjects {

    final class Initializer {

        private Initializer() {
        }

        static public ActivityComponent init(final ApplicationComponent applicationComponent, final BaseActivity activity) {
            return DaggerActivityComponent.builder()
                                          .applicationComponent(applicationComponent)
                                          .activityModule(new ActivityModule(activity))
                                          .build();
        }
    }

    NewsListPresenter getNewsListPresenter();

    NewsListAdapter getNewsListAdapter();

    NewsRouter getNewsRouter();
}

