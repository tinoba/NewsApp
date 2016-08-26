package eu.fiveminutes.newsapp.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.model.NewsArticle;

public final class NewsDetailFragment extends Fragment {

    private static final String NEWS_DETAIL = "NEWS_DETAIL";

    private NewsArticle article;
    private NewsArticleParcelable articleParcelable;
    private TitleListener titleListener;

    @BindView(R.id.webViewNews)
    protected WebView webviewNews;

    public static NewsDetailFragment getNewsDetailFragment(final NewsArticle article) {
        final NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        final Bundle bundle = new Bundle();

        bundle.putParcelable(NEWS_DETAIL, new NewsArticleParcelable(article));
        newsDetailFragment.setArguments(bundle);

        return newsDetailFragment;
    }

    @Override
    public final View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_news_detail, container, false);
        ButterKnife.bind(this, view);

        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            articleParcelable = arguments.getParcelable(NEWS_DETAIL);
            article = articleParcelable.toNewsArticle();
        }
        webviewNews.setWebViewClient(new WebViewClient());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        webviewNews.loadUrl(article.webUrl);
        titleListener.setTitle(article.mainHeadline);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (context instanceof TitleListener) {
            titleListener = (TitleListener) context;
        } else {
            throw new RuntimeException(getString(R.string.interfaceException, context.toString(), TitleListener.class.toString()));
        }
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

        public NewsArticle toNewsArticle() {
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
