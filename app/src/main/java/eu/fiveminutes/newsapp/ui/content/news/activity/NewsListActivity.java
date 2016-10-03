package eu.fiveminutes.newsapp.ui.content.news.activity;

import android.os.Bundle;

import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.injection.component.ActivityComponent;
import eu.fiveminutes.newsapp.ui.content.base.activities.BaseActivity;
import eu.fiveminutes.newsapp.ui.content.news.fragment.NewsListFragment;
import eu.fiveminutes.newsapp.ui.interfaces.TitleListener;

public final class NewsListActivity extends BaseActivity implements TitleListener {

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        if (savedInstanceState == null) {
            attachFragment();
        }
    }

    private void attachFragment() {
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.fragment_container, new NewsListFragment())
                                   .commit();
    }

    @Override
    public void setTitle(final String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}
