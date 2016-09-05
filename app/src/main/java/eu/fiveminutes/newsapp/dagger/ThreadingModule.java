package eu.fiveminutes.newsapp.dagger;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public final class ThreadingModule {

    public static final String OBSERVE_SCHEDULER = "SubscribeScheduler";
    public static final String SUBSCRIBE_SCHEDULER = "ObserveScheduler";

    @Provides
    @Singleton
    @Named(OBSERVE_SCHEDULER)
    Scheduler provideAndroidSchedulersMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(SUBSCRIBE_SCHEDULER)
    Scheduler provideSchedulersIo() {
        return Schedulers.io();
    }
}
