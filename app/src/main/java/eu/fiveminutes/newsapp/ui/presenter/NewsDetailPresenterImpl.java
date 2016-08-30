package eu.fiveminutes.newsapp.ui.presenter;

import java.lang.ref.WeakReference;

public final class NewsDetailPresenterImpl implements NewsDetailPresenter {

    private WeakReference<NewsDetailView> newsDetailViewWeakReference;

    public NewsDetailPresenterImpl() {

    }

    @Override
    public void setView(final NewsDetailView view) {
        newsDetailViewWeakReference = new WeakReference<>(view);
    }
}
