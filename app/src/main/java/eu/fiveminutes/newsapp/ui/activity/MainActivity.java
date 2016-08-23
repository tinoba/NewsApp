package eu.fiveminutes.newsapp.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import eu.fiveminutes.newsapp.application.ObjectGraph;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.application.NewsApp;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListViewModel;
import eu.fiveminutes.newsapp.utils.NetworkInformation;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListView;

public final class MainActivity extends AppCompatActivity implements NewsListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.activity_main_news_list)
    protected ListView activityMainNewsList;

    @BindView(R.id.activity_main_news_swipe)
    protected SwipeRefreshLayout newsSwipe;

    @BindView(R.id.activity_main_error_message)
    protected TextView errorMessage;

    private NewsListPresenter presenter;
    private NewsListAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ObjectGraph objectGraph = ((NewsApp) getApplication()).getObjectGraph();
        presenter = objectGraph.createNewsListPresenter();
        newsSwipe.setOnRefreshListener(this);
        setNewsListAdapter();
    }

    @OnItemClick(R.id.activity_main_news_list)
    public void onItemClick(int position) {
        final NewsArticle article = newsAdapter.getItem(position);
        final Intent intent = NewsDetailActivity.createIntent(MainActivity.this, article);
        startActivity(intent);
    }

    public void setNewsListAdapter() {
        newsAdapter = new NewsListAdapter(this);
        activityMainNewsList.setAdapter(newsAdapter);
    }

    @Override
    protected void onResume() {
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
        if (viewModel.showTextBox) {
            errorMessage.setText(String.valueOf(R.string.news_database_error_text));
            errorMessage.setVisibility(View.VISIBLE);
        } else {
            errorMessage.setVisibility(View.GONE);
            showNews(viewModel.articles);
        }
        newsSwipe.setRefreshing(viewModel.showRefreshing);
    }

    @Override
    public void showErrorMessage(final String message) {
        showToast(message);
    }

    private void showToast(final String message) {
        final Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onRefresh() {
        presenter.loadNews();
    }
}
