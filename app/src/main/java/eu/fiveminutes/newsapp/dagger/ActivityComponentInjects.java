package eu.fiveminutes.newsapp.dagger;

import eu.fiveminutes.newsapp.ui.fragment.NewsDetailFragment;
import eu.fiveminutes.newsapp.ui.fragment.NewsListFragment;

public interface ActivityComponentInjects {

    void injectNewsListFragment(NewsListFragment fragment);

    void injectNewsDetailFragment(NewsDetailFragment fragment);
}
