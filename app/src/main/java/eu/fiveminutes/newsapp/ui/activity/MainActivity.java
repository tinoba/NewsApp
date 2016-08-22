package eu.fiveminutes.newsapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import eu.fiveminutes.newsapp.application.ObjectGraph;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.application.NewsApp;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListView;

public final class MainActivity extends AppCompatActivity implements NewsListView {

    @BindView(R.id.activity_main_news_list)
    protected ListView activity_main_news_list;

    private NewsListPresenter presenter;
    private NewsListAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ObjectGraph objectGraph = ((NewsApp) getApplication()).getObjectGraph();
        presenter = objectGraph.createNewsListPresenter();
        setNewsListAdapter();
    }

    @OnItemClick(R.id.activity_main_news_list)
        public void onItemClick(int position) {
                final NewsArticle article = newsAdapter.getItem(position);
                final Intent intent = NewsDetailActivity.createIntent(MainActivity.this,article);
                startActivity(intent);
            }

    private boolean isNetworkConnected() {
        final ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void setNewsListAdapter(){
        newsAdapter = new NewsListAdapter(this);
        activity_main_news_list.setAdapter(newsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        if(isNetworkConnected()) {
            presenter.loadNews();
        }
        else{
            presenter.loadNewsDao();
        }
    }

    public void showNews(List<NewsArticle> articles) {
        newsAdapter.clear();
        newsAdapter.addAll(articles);
        activity_main_news_list.deferNotifyDataSetChanged();
    }

    @Override
    public void renderView(List<NewsArticle> newsArticles) {
        showNews(newsArticles);
    }
}
