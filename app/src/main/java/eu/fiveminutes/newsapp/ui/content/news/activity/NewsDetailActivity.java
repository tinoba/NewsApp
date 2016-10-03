package eu.fiveminutes.newsapp.ui.content.news.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.domain.model.NewsArticle;

public final class NewsDetailActivity extends AppCompatActivity {

    private static final String NEWS_DETAIL = "NEWS_DETAIL";

    private NewsArticle article;

    @BindView(R.id.news_detail_fragment_web_view)
    protected WebView newsDetailWebView;

    public static Intent createIntent(final Context context, final NewsArticle article) {
        final Intent intent = new Intent(context, NewsDetailActivity.class);
        NewsArticleParcelable articleParcelable = new NewsArticleParcelable(article);
        intent.putExtra(NEWS_DETAIL, articleParcelable);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_detail);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        final NewsArticleParcelable articleParcelable = intent.getExtras().getParcelable(NEWS_DETAIL);
        article = articleParcelable.toMewsArticle();
        newsDetailWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void onResume() {
        super.onResume();

        newsDetailWebView.loadUrl(article.webUrl);
        getSupportActionBar().setTitle(article.mainHeadline);
    }

    public static final class NewsArticleParcelable implements Parcelable {

        public final String webUrl;
        public final String snippet;
        public final String mainHeadline;
        public final Uri imgUri;

        public NewsArticleParcelable(NewsArticle article) {
            this.webUrl = article.webUrl;
            this.snippet = article.snippet;
            this.mainHeadline = article.mainHeadline;
            this.imgUri = article.imgUri;
        }

        public NewsArticle toMewsArticle() {
            return new NewsArticle(mainHeadline, snippet, webUrl, imgUri);
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.webUrl);
            dest.writeString(this.snippet);
            dest.writeString(this.mainHeadline);
            dest.writeParcelable(this.imgUri, flags);
        }

        protected NewsArticleParcelable(Parcel in) {
            this.webUrl = in.readString();
            this.snippet = in.readString();
            this.mainHeadline = in.readString();
            this.imgUri = in.readParcelable(Uri.class.getClassLoader());
        }

        public static final Creator<NewsArticleParcelable> CREATOR = new Creator<NewsArticleParcelable>() {

            @Override
            public NewsArticleParcelable createFromParcel(Parcel source) {return new NewsArticleParcelable(source);}

            @Override
            public NewsArticleParcelable[] newArray(int size) {return new NewsArticleParcelable[size];}
        };
    }
}
