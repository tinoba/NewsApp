package eu.fiveminutes.newsapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.dagger.ActivityComponent;
import eu.fiveminutes.newsapp.dagger.ComponentFactory;

public abstract class BaseFragment extends Fragment {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final NewsApplication newsApplication = (NewsApplication) getActivity().getApplication();

        activityComponent = ComponentFactory.createActivityComponent(newsApplication, getActivity());
        inject(activityComponent);
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
