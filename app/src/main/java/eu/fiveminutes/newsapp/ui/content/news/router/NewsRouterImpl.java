package eu.fiveminutes.newsapp.ui.content.news.router;

import android.support.v4.app.FragmentManager;

import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.domain.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.content.news.fragment.NewsDetailFragment;

public final class NewsRouterImpl implements NewsRouter {

    private final FragmentManager fragmentManager;

    public NewsRouterImpl(final FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void goToDetail(final NewsArticle article) {
        fragmentManager.beginTransaction()
                       .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                       .replace(R.id.fragment_container, NewsDetailFragment.newInstance(article))
                       .addToBackStack(null)
                       .commit();
    }
}
