package eu.fiveminutes.newsapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.model.NewsArticle;

public final class NewsListAdapter extends ArrayAdapter<NewsArticle> {

    public static final int NEWS_LIST_IMAGE_SIZE = 200;

    private final LayoutInflater inflater;
    private final Context context;

    public NewsListAdapter(Context context) {
        super(context, R.layout.news_list_row, new ArrayList<NewsArticle>());
        inflater = LayoutInflater.from(context);
        this.context = context;
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
        Picasso.with(context)
               .load(article.imgUri)
               .resize(NEWS_LIST_IMAGE_SIZE, NEWS_LIST_IMAGE_SIZE)
               .centerCrop()
               .placeholder(R.mipmap.ic_launcher)
               .into(holder.newsListImage);
        holder.newsListSnippet.setText(article.snippet);

        return view;
    }

    static final class ViewHolder {

        @BindView(R.id.txtHeadline)
        TextView txtHeadLine;

        @BindView(R.id.activity_main_news_list_image)
        ImageView newsListImage;

        @BindView(R.id.activity_main_news_list_snippet)
        TextView newsListSnippet;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
