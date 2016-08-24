package eu.fiveminutes.newsapp.ui.presenter;

public interface NewsListView {

    void renderView(NewsListViewModel viewModel);

    void showErrorMessage(String message);
}
