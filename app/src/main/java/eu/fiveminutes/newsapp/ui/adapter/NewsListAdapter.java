package eu.fiveminutes.newsapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.model.NewsArticle;

public final class NewsListAdapter extends ArrayAdapter<NewsArticle> {

    private final LayoutInflater inflater;

    public NewsListAdapter(Context context) {
        super(context, R.layout.news_list_row, new ArrayList<NewsArticle>());
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.news_list_row, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        final NewsArticle article = getItem(position);
        holder.txtHeadLine.setText(article.mainHeadline);

        return view;
    }

    static final class ViewHolder {

        @BindView(R.id.txtHeadline)
        TextView txtHeadLine;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
