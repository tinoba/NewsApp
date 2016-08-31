package eu.fiveminutes.newsapp.dagger;

import android.net.ConnectivityManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.utils.NetworkInformationImpl;
import eu.fiveminutes.newsapp.utils.ResourceUtils;
import eu.fiveminutes.newsapp.utils.ResourceUtilsImpl;

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
    ResourceUtils provideResourceUtils(NewsApplication application) {
        return new ResourceUtilsImpl(application.getResources());
    }
}
