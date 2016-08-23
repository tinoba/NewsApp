package eu.fiveminutes.newsapp.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
    protected ListView activity_main_news_list;

    @BindView(R.id.activity_main_news_swipe)
    protected SwipeRefreshLayout activity_main_news_swipe;

    private NewsListPresenter presenter;
    private NewsListAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ObjectGraph objectGraph = ((NewsApp) getApplication()).getObjectGraph();
        presenter = objectGraph.createNewsListPresenter();
        activity_main_news_swipe.setOnRefreshListener(this);
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
        activity_main_news_list.setAdapter(newsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadNews();
    }

    public void showNews(NewsListViewModel viewModel) {
        newsAdapter.clear();
        newsAdapter.addAll(viewModel.articles);
        activity_main_news_list.deferNotifyDataSetChanged();
        activity_main_news_swipe.setRefreshing(viewModel.showRefreshing);
    }

    @Override
    public void renderView(NewsListViewModel viewModel) {
        showNews(viewModel);
    }

    @Override
    public void onRefresh() {
        presenter.loadNews();
    }
}
