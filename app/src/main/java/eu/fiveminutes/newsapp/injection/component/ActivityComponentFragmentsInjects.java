package eu.fiveminutes.newsapp.injection.component;

import eu.fiveminutes.newsapp.ui.content.news.fragment.NewsDetailFragment;
import eu.fiveminutes.newsapp.ui.content.news.fragment.NewsListFragment;

public interface ActivityComponentFragmentsInjects {

    void inject(NewsListFragment fragment);

    void inject(NewsDetailFragment fragment);
}
