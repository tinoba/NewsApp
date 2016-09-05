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

    @Provides
    @Singleton
    @Named("MainThreadScheduler")
    Scheduler provideAndroidSchedulersMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named("IoScheduler")
    Scheduler provideSchedulersIo() {
        return Schedulers.io();
    }
}
