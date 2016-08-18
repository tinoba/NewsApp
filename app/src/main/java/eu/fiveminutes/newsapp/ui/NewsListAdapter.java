package eu.fiveminutes.newsapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.model.NewsArticle;

public class NewsListAdapter extends ArrayAdapter<NewsArticle>{

    private final List<NewsArticle> articles;

    public NewsListAdapter(Context context, List<NewsArticle> articles) {
        super(context, R.layout.news_list_row,articles);
        this.articles = articles;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.news_list_row, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.txtHeadLine.setText(articles.get(position).mainHeadline);
        holder.txtParagraph.setText(articles.get(position).snippet);
        holder.txtUrl.setText(articles.get(position).webUrl);

        return view;
    }
    static class ViewHolder {
        @BindView(R.id.txtHeadline)
        TextView txtHeadLine;
        @BindView(R.id.txtParagraph)
        TextView txtParagraph;
        @BindView(R.id.txtUrl)
        TextView txtUrl;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
