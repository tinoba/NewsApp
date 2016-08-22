package eu.fiveminutes.newsapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.model.NewsArticle;

public final class NewsDetailActivity extends AppCompatActivity {

    private static final String NEWS_DETAIL = "NEWS DETAIL";

    private NewsArticle article;

    @BindView(R.id.webViewNews)
    protected WebView webviewNews;

    public static Intent createIntent(final Context context, final NewsArticle article) {
        final Intent intent = new Intent(context, NewsDetailActivity.class);
        NewsArticleParcelable articleParcelable = new NewsArticleParcelable(article);
        intent.putExtra(NEWS_DETAIL, articleParcelable);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        final NewsArticleParcelable articleParcelable = intent.getExtras().getParcelable(NEWS_DETAIL);
        article = articleParcelable.toMewsArticle();
        webviewNews.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void onResume() {
        super.onResume();

        webviewNews.loadUrl(article.webUrl);
        getSupportActionBar().setTitle(article.mainHeadline);
    }

    public static final class NewsArticleParcelable implements Parcelable {

        public final String webUrl;
        public final String snippet;
        public final String mainHeadline;

        public NewsArticleParcelable(NewsArticle article) {
            this.webUrl = article.webUrl;
            this.snippet = article.snippet;
            this.mainHeadline = article.mainHeadline;
        }

        public NewsArticle toMewsArticle() {
            return new NewsArticle(mainHeadline, snippet, webUrl);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.webUrl);
            dest.writeString(this.snippet);
            dest.writeString(this.mainHeadline);
        }

        protected NewsArticleParcelable(Parcel in) {
            this.webUrl = in.readString();
            this.snippet = in.readString();
            this.mainHeadline = in.readString();
        }

        public static final Parcelable.Creator<NewsArticleParcelable> CREATOR = new Parcelable.Creator<NewsArticleParcelable>() {
            @Override
            public NewsArticleParcelable createFromParcel(Parcel source) {
                return new NewsArticleParcelable(source);
            }

            @Override
            public NewsArticleParcelable[] newArray(int size) {
                return new NewsArticleParcelable[size];
            }
        };
    }
}
