package eu.fiveminutes.newsapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.fragment.NewsDetailFragment;
import eu.fiveminutes.newsapp.ui.fragment.NewsListFragment;
import eu.fiveminutes.newsapp.ui.fragment.TitleListener;

import static eu.fiveminutes.newsapp.ui.fragment.NewsListFragment.NewsListFragmentListener;

public final class MainActivity extends AppCompatActivity implements TitleListener, NewsListFragmentListener {

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

    @Override
    public void showDetailFragment(final NewsArticle article) {
        getSupportFragmentManager().beginTransaction()
                                   .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                                   .replace(R.id.fragment_container, NewsDetailFragment.newInstance(article))
                                   .addToBackStack(null)
                                   .commit();
    }
}
