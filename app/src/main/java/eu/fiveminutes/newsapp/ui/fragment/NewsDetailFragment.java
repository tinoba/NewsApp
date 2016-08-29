package eu.fiveminutes.newsapp.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.fiveminutes.news_app_2.R;
import eu.fiveminutes.newsapp.application.NewsApp;
import eu.fiveminutes.newsapp.application.ObjectGraph;
import eu.fiveminutes.newsapp.model.NewsArticle;
import eu.fiveminutes.newsapp.ui.presenter.NewsDetailPresenter;
import eu.fiveminutes.newsapp.ui.presenter.NewsDetailView;

public final class NewsDetailFragment extends Fragment implements NewsDetailView {

    private static final String NEWS_DETAIL = "NEWS_DETAIL";

    private NewsArticle article;
    private NewsArticleParcelable articleParcelable;
    private TitleListener titleListener;
    private NewsDetailPresenter presenter;

    @BindView(R.id.news_detail_fragment_web_view)
    protected WebView newsDetailWebView;

    @BindView(R.id.news_detail_loading_bar)
    protected ProgressBar newsDetailLoadingBar;

    private void setWebView() {
        newsDetailWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        newsDetailWebView.loadUrl(article.webUrl);
    }

    public static NewsDetailFragment newInstance(final NewsArticle article) {
        final NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        final Bundle bundle = new Bundle();

        bundle.putParcelable(NEWS_DETAIL, new NewsArticleParcelable(article));
        newsDetailFragment.setArguments(bundle);

        return newsDetailFragment;
    }

    private void setWebViewSettings() {
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ObjectGraph objectGraph = ((NewsApp) getActivity().getApplication()).getObjectGraph();
        presenter = objectGraph.provideNewsDetailPresenter();
    }

    @Override
    public final View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        setWebViewSettings();
        final Bundle arguments = this.getArguments();
        if (arguments != null) {
            articleParcelable = arguments.getParcelable(NEWS_DETAIL);
            article = articleParcelable.toNewsArticle();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.setView(this);
        setWebView();
        titleListener.setTitle(article.mainHeadline);
        newsDetailWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);
                newsDetailLoadingBar.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(final WebView view, final WebResourceRequest request, final WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.stopLoading();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.news_detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.news_detail_refresh:
                newsDetailWebView.loadUrl(article.webUrl);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        newsDetailWebView.stopLoading();
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
