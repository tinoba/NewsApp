package eu.fiveminutes.newsapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import eu.fiveminutes.newsapp.application.NewsApp;
import eu.fiveminutes.newsapp.application.ObjectGraph;

public abstract class BaseFragment extends Fragment {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objectGraph = ((NewsApp) getActivity().getApplication()).getObjectGraph();
        inject(objectGraph);
    }

    protected abstract void inject(ObjectGraph objectGraph);
}
