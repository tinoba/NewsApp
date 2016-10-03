package eu.fiveminutes.newsapp.util;

import android.net.ConnectivityManager;

public class NetworkInformationImpl implements NetworkInformation {

    private final ConnectivityManager connectivityManager;

    public NetworkInformationImpl(final ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public boolean isConnected() {
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
