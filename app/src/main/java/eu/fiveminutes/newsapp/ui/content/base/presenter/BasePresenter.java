package eu.fiveminutes.newsapp.ui.content.base.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {

    private CompositeSubscription compositeSubscription;

    public void addSubscription(final Subscription subscription) {
        if (compositeSubscription == null || compositeSubscription.isUnsubscribed()) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    public void unsubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }
}
