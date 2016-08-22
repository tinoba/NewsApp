package eu.fiveminutes.newsapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import eu.fiveminutes.newsapp.api.ObjectGraph;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.application.NewsApp;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.adapter.NewsListAdapter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsListView;

public final class MainActivity extends AppCompatActivity implements NewsListView {

    @BindView(R.id.listViewNews)
    protected ListView listViewNews;

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

        listViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final NewsArticle article = newsAdapter.getItem(position);
                final Intent intent = NewsDetailActivity.createIntent(MainActivity.this,article);
                startActivity(intent);
            }
        });
    }

    public void setNewsListAdapter(){
        newsAdapter = new NewsListAdapter(this);
        listViewNews.setAdapter(newsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadNews();
    }

    public void showNews(List<NewsArticle> articles) {
        newsAdapter.clear();
        newsAdapter.addAll(articles);
        listViewNews.deferNotifyDataSetChanged();
    }

    @Override
    public void renderView(List<NewsArticle> newsArticles) {
        showNews(newsArticles);
    }
}
