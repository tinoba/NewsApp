package eu.fiveminutes.newsapp.ui.content.news.presenter;

import java.lang.ref.WeakReference;

import eu.fiveminutes.newsapp.ui.content.news.view.NewsDetailView;

public final class NewsDetailPresenterImpl implements NewsDetailPresenter {

    private WeakReference<NewsDetailView> newsDetailViewWeakReference;

    public NewsDetailPresenterImpl() {

    }

    @Override
    public void setView(final NewsDetailView view) {
        newsDetailViewWeakReference = new WeakReference<>(view);
    }
}
