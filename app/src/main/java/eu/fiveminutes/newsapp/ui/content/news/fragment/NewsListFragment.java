package eu.fiveminutes.newsapp.ui.content.news.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.domain.model.NewsArticle;
import eu.fiveminutes.newsapp.injection.component.ActivityComponent;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.content.base.fragments.BaseFragment;
import eu.fiveminutes.newsapp.ui.content.news.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.content.news.view.NewsListView;
import eu.fiveminutes.newsapp.ui.content.news.viewmodel.NewsListViewModel;
import eu.fiveminutes.newsapp.ui.interfaces.TitleListener;

public final class NewsListFragment extends BaseFragment implements NewsListView, OnRefreshListener {

    @Inject
    NewsListPresenter presenter;

    @Inject
    NewsListAdapter newsAdapter;

    @BindView(R.id.activity_main_news_list)
    protected ListView activityMainNewsList;

    @BindView(R.id.activity_main_news_swipe)
    protected SwipeRefreshLayout newsSwipe;

    @BindView(R.id.activity_main_error_message)
    protected TextView errorMessage;

    @BindView(R.id.news_list_loading_bar)
    protected ProgressBar newsListLoadingBar;
    private TitleListener titleListener;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        titleListener.setTitle(getString(R.string.main_activity_name));
    }

    @Override
    public final View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                   final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.bind(this, view);

        setNewsListAdapter();
        newsSwipe.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        if (context instanceof TitleListener) {
            titleListener = (TitleListener) context;
        } else {
            throw new ClassCastException(getString(R.string.interfaceException, context.toString(), TitleListener.class.toString()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.activate();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    protected void inject(final ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @OnItemClick(R.id.activity_main_news_list)
    public void onItemClick(final int position) {
        final NewsArticle article = newsAdapter.getItem(position);

        presenter.goToDetailFragment(article);
    }

    public void setNewsListAdapter() {
        newsAdapter = new NewsListAdapter(getActivity());
        activityMainNewsList.setAdapter(newsAdapter);
    }

    public void showNews(final List<NewsArticle> articles) {
        newsAdapter.clear();
        newsAdapter.addAll(articles);
    }

    private void showToast(final String message) {
        final Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void removeLoadingBar() {
        newsListLoadingBar.setVisibility(View.GONE);
        activityMainNewsList.setVisibility(View.VISIBLE);
    }

    @Override
    public void renderView(final NewsListViewModel viewModel) {
        errorMessage.setVisibility(viewModel.showTextBox ? View.VISIBLE : View.GONE);
        if (viewModel.showTextBox) {
            errorMessage.setText(getString(R.string.news_database_error_text));
        } else {
            showNews(viewModel.articles);
        }
        removeLoadingBar();
        newsSwipe.setRefreshing(viewModel.showRefreshing);
    }

    @Override
    public void showErrorMessage(final String message) {
        showToast(message);
    }

    @Override
    public void onRefresh() {
        presenter.loadNews();
    }
}
