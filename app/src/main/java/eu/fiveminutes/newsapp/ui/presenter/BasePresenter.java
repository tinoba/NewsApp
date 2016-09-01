package eu.fiveminutes.newsapp.ui.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {

    private CompositeSubscription compositeSubscription;

    void addSubscribe(Subscription subscription) {
        if (compositeSubscription == null || compositeSubscription.isUnsubscribed()) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (compositeSubscription == null) {
            compositeSubscription.unsubscribe();
        }
    }
}
