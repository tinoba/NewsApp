package eu.fiveminutes.newsapp.ui.content.news.view;

import eu.fiveminutes.newsapp.ui.content.news.viewmodel.NewsListViewModel;

public interface NewsListView {

    void renderView(NewsListViewModel viewModel);

    void showErrorMessage(String message);
}
