package eu.fiveminutes.newsapp.ui.presenter;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkInformationImpl implements NetworkInformation {
    @Override
    public Boolean isConnected(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
