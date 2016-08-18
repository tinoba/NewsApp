package eu.fiveminutes.newsapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import eu.fiveminutes.newsapp.api.ObjectGraph;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.application.NewsApp;

public final class MainActivity extends AppCompatActivity {

    private ObjectGraph objectGraph;

    private NewsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        objectGraph = ((NewsApp) getApplication()).getObjectGraph();
        presenter = objectGraph.createNewsListPresenter();
    }

    @OnClick(R.id.btnDownlaod)
    public void download() {
        presenter.loadRetroData();
    }
}
