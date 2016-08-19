package eu.fiveminutes.newsapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import eu.fiveminutes.newsapp.api.ObjectGraph;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.application.NewsApp;
import eu.fiveminutes.newsapp.model.NewsArticle;

public final class MainActivity extends AppCompatActivity implements NewsListView {

    private ObjectGraph objectGraph;
    private NewsListPresenter presenter;

    @BindView(R.id.listViewNews)
    ListView listViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        objectGraph = ((NewsApp) getApplication()).getObjectGraph();
        presenter = objectGraph.createNewsListPresenter();

        presenter.setView(this);
    }

    @OnClick(R.id.btnDownlaod)
    public void download() {
        presenter.loadNews();
    }

    public void showNews(List<NewsArticle> articles){
        NewsListAdapter newsAdapter = new NewsListAdapter(this,articles);
        listViewNews.setAdapter(newsAdapter);
    }

    @Override
    public void renderView(NewsListViewModel viewModel) {

    }
}
