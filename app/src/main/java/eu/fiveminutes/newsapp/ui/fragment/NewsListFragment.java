package eu.fiveminutes.newsapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.application.NewsApp;
import eu.fiveminutes.newsapp.application.ObjectGraph;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListView;
import eu.fiveminutes.newsapp.ui.presenter.NewsListViewModel;

public final class NewsListFragment extends Fragment implements NewsListView, OnRefreshListener {

    public interface NewsListFragmentListener {

        void showDetailFragment(NewsArticle article);
    }

    @BindView(R.id.activity_main_news_list)
    protected ListView activityMainNewsList;

    @BindView(R.id.activity_main_news_swipe)
    protected SwipeRefreshLayout newsSwipe;

    @BindView(R.id.activity_main_error_message)
    protected TextView errorMessage;

    private NewsListPresenter presenter;
    private TitleListener titleListener;
    private NewsListAdapter newsAdapter;
    private NewsListFragmentListener newsListFragmentListener;

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        if (context instanceof TitleListener) {
            titleListener = (TitleListener) context;
        } else {
            throw new ClassCastException(context.toString() + getString(R.string.interfaceException));
        }

        if (context instanceof NewsListFragmentListener) {
            newsListFragmentListener = (NewsListFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + getString(R.string.interfaceException));
        }
    }

    @Override
    public final View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                   final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);

        titleListener.setTitle(getString(R.string.main_activity_name));
        final ObjectGraph objectGraph = ((NewsApp) getActivity().getApplication()).getObjectGraph();
        presenter = objectGraph.createNewsListPresenter();
        newsSwipe.setOnRefreshListener(this);
        setNewsListAdapter();
        return view;
    }

    @OnItemClick(R.id.activity_main_news_list)
    public void onItemClick(final int position) {
        final NewsArticle article = newsAdapter.getItem(position);

        newsListFragmentListener.showDetailFragment(article);
    }

    public void setNewsListAdapter() {
        newsAdapter = new NewsListAdapter(getActivity());
        activityMainNewsList.setAdapter(newsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadNews();
    }

    public void showNews(final List<NewsArticle> articles) {
        newsAdapter.clear();
        newsAdapter.addAll(articles);
    }

    @Override
    public void renderView(final NewsListViewModel viewModel) {
        errorMessage.setVisibility(viewModel.showTextBox ? View.VISIBLE : View.GONE);
        if (viewModel.showTextBox) {
            errorMessage.setText(getString(R.string.news_database_error_text));
        } else {
            showNews(viewModel.articles);
        }
        newsSwipe.setRefreshing(viewModel.showRefreshing);
    }

    @Override
    public void showErrorMessage(final String message) {
        showToast(message);
    }

    private void showToast(final String message) {
        final Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onRefresh() {
        presenter.loadNews();
    }
}
