package eu.fiveminutes.newsapp.ui;

import java.util.List;

public final class NewsListViewModel {

    public final List<NewsViewModel> newsViewModels;

    public NewsListViewModel(List<NewsViewModel> newsViewModels) {
        this.newsViewModels = newsViewModels;
    }
}
