package eu.fiveminutes.newsapp.ui.content.base.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import eu.fiveminutes.newsapp.application.NewsApplication;
import eu.fiveminutes.newsapp.injection.component.ActivityComponent;
import eu.fiveminutes.newsapp.ui.content.base.activities.BaseActivity;

public abstract class BaseFragment extends Fragment {

    private ActivityComponent activityComponent;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final NewsApplication newsApplication = (NewsApplication) getActivity().getApplication();

        injectMe(newsApplication);
    }

    private void injectMe(NewsApplication newsApplication) {
        inject(((BaseActivity) getActivity()).getActivityComponent(newsApplication));
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
