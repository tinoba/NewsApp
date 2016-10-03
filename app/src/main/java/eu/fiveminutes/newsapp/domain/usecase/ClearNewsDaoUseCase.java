package eu.fiveminutes.newsapp.domain.usecase;

import rx.Completable;

public interface ClearNewsDaoUseCase {

    Completable execute();
}
