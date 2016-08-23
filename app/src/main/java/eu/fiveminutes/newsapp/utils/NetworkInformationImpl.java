package eu.fiveminutes.newsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import eu.fiveminutes.newsapp.utils.NetworkInformation;

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
