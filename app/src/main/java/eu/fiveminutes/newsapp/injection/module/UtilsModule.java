package eu.fiveminutes.newsapp.injection.module;

import android.net.ConnectivityManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.util.NetworkInformation;
import eu.fiveminutes.newsapp.util.NetworkInformationImpl;
import eu.fiveminutes.newsapp.util.ResourceUtils;
import eu.fiveminutes.newsapp.util.ResourceUtilsImpl;

@Module
public final class UtilsModule {

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(final NewsApplication application) {
        return (ConnectivityManager) application.getSystemService(application.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    NetworkInformation provideNetworkInformation(final ConnectivityManager connectivityManager) {
        return new NetworkInformationImpl(connectivityManager);
    }

    @Provides
    @Singleton
    ResourceUtils provideResourceUtils(final NewsApplication application) {
        return new ResourceUtilsImpl(application.getResources());
    }
}
