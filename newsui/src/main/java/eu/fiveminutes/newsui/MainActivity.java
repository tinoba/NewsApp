package eu.fiveminutes.newsui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

final public class MainActivity extends AppCompatActivity {
    @BindView(R.id.listViewNews)
    ListView ListViewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ArrayList<String> textList = new ArrayList<>();
        textList.add("nesto");
        textList.add("nesto1");
        textList.add("nesto2");
        NewsListAdapter customAdapter = new NewsListAdapter(this,textList);
        ListViewNews.setAdapter(customAdapter);

    }
}
